package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.TopDto;
import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.entity.HandWashData;
import haoc.fiap.healthbackend.entity.Job;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.UserMapper;
import haoc.fiap.healthbackend.repository.HandWashRepository;
import haoc.fiap.healthbackend.repository.JobRepository;
import haoc.fiap.healthbackend.repository.UserRepository;
import haoc.fiap.healthbackend.resquest.LoginRequest;
import haoc.fiap.healthbackend.resquest.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import haoc.fiap.healthbackend.jwt.JwtUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtil jwtUtil;

    @Autowired
    private UserRepository repository;

    @Autowired
    private HandWashRepository handWashRepository;

    @Autowired
    private JobRepository jobRepository;

    public UserDto registerUser(UserRequest user) throws Exception{
        if(user.getPassword().length() < 8){
            throw new Exception("Senha menor que 8 caracteres");
        }
        Job job = jobRepository.findByName(user.getJob().getName());
        if(job != null){
            user.setJob(job);
        }
        User response = repository.save(UserMapper.toUser(user));
        registerWashData(response);
        return UserMapper.userToDto(response);
    }

    public UserDto findByEmail(String email) {
        User user = repository.findByEmail(email);
        if(user != null){
            return UserMapper.userToDto(user);
        } else return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), new ArrayList<>());
    }

    public String getToken(LoginRequest request) {
        return this.jwtUtil.generateToken(repository.findByEmail(request.getEmail()));
    }

    public Integer getUserScore(Integer id) throws Exception {
        try{
            Optional<User> user = repository.findById(id);

            if(user.isPresent()) {
                return repository.getUserScore(id);
            }

            throw new Exception("Usuário não encontrado");

        } catch(Exception e){
            throw new Exception(e.getMessage(), e);
        }
    }

    private void registerWashData(User user){
        handWashRepository.save(HandWashData.builder()
                        .userId(user.getId())
                        .month(LocalDate.now().getMonth().toString())
                        .countMonday(0)
                        .countTuesday(0)
                        .countWednesday(0)
                        .countThursday(0)
                        .countFriday(0)
                        .countSaturday(0)
                        .countSunday(0)
                .build());
    }

    public List<TopDto> getTopList() throws Exception{
        try{
            List<UserDto> userDtoList = repository.getTopList().stream()
                    .map(UserMapper::userToDto)
                    .collect(Collectors.toList());

            if(!userDtoList.isEmpty()) {
                List<TopDto> topDtoList = new ArrayList<>();
                userDtoList.forEach(user -> {
                    topDtoList.add(TopDto.builder()
                                    .name(user.getName())
                                    .lastName(user.getLastName())
                                    .score(user.getScore())
                            .build());
                });

                return topDtoList;
            }

            throw new Exception("Não foi possível encontrar o top 3");
        } catch (Exception e){
            throw new Exception(e.getMessage(), e);
        }

    }
}
