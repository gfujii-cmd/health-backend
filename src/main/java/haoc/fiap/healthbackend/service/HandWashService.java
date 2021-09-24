package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.HandWashDataDto;
import haoc.fiap.healthbackend.entity.HandWashData;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.HandWashDataMapper;
import haoc.fiap.healthbackend.repository.HandWashRepository;
import haoc.fiap.healthbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HandWashService {

    @Autowired
    private HandWashRepository handWashRepository;

    @Autowired
    private UserRepository userRepository;

    public HandWashDataDto getHandWashDataFrom(String email) throws Exception {
        try{
            Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
            if(user.isPresent()) {
                Optional<HandWashData> handWashData = handWashRepository.findById(user.get().getId());

                if(handWashData.isPresent()) {
                    return HandWashDataMapper.toDto(handWashData.get());
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }
}
