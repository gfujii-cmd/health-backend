package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.TopDto;
import haoc.fiap.healthbackend.dto.UserDto;
import haoc.fiap.healthbackend.entity.Badge;
import haoc.fiap.healthbackend.entity.HandWashData;
import haoc.fiap.healthbackend.entity.Job;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.UserMapper;
import haoc.fiap.healthbackend.repository.BadgeRepository;
import haoc.fiap.healthbackend.repository.HandWashRepository;
import haoc.fiap.healthbackend.repository.JobRepository;
import haoc.fiap.healthbackend.repository.UserRepository;
import haoc.fiap.healthbackend.resquest.LoginRequest;
import haoc.fiap.healthbackend.resquest.UserRequest;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import haoc.fiap.healthbackend.jwt.JwtUtil;
import org.springframework.web.server.ResponseStatusException;

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
    private UserRepository userRepository;

    @Autowired
    private HandWashRepository handWashRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    public UserDto registerUser(UserRequest user) throws Exception{
        if(user.getPassword().length() < 8){
            throw new Exception("Senha menor que 8 caracteres");
        }

        Optional<User> userInDatabase = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        Badge badge = badgeRepository.findByLevel(1);
        if(userInDatabase.isPresent()){
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, "Usu??rio j?? existe");
        }
        Optional<Job> job = jobRepository.findByName(user.getJob().getName());
        if(job.isPresent()){
            user.setJob(job.get());
            User response = userRepository.save(UserMapper.toUser(user, badge));
            registerWashData(response);
            return UserMapper.userToDto(response);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job " + user.getJob().getName() + " n??o existe!");
        }
    }

    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null){
            return UserMapper.userToDto(user);
        } else return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), new ArrayList<>());
    }

    public String getToken(LoginRequest request) {
        return this.jwtUtil.generateToken(userRepository.findByEmail(request.getEmail()));
    }

    public Integer getUserScore(String email) throws Exception {
        try{
            Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

            if(user.isPresent()) {
                return userRepository.getUserScore(email);
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usu??rio n??o encontrado");

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
            List<UserDto> userDtoList = userRepository.getTopList().stream()
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

            throw new Exception("N??o foi poss??vel encontrar o top 3");
        } catch (Exception e){
            throw new Exception(e.getMessage(), e);
        }
    }

    public UserDto setRfid(String email, String rfid) throws Exception {
        try{
            Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

            if(user.isPresent()){
                user.get().setRfid(rfid);

                return UserMapper.userToDto(userRepository.save(user.get()));
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usu??rio n??o encontrado");

        } catch (Exception e){
            throw new Exception(e.getMessage(), e);
        }
    }

    public UserDto setHour(Integer entry, Integer exit, String rfid){
        Optional<User> user = userRepository.findByRfid(rfid);
        if(user.isPresent()){
            user.get().setEntryHour(entry);
            user.get().setExitHour(exit);

            return UserMapper.userToDto(userRepository.save(user.get()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usu??rio n??o encontrado");
        }
    }
}
