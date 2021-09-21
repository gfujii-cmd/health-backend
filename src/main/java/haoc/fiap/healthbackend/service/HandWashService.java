package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.HandWashDataDto;
import haoc.fiap.healthbackend.entity.HandWashData;
import haoc.fiap.healthbackend.mapper.HandWashDataMapper;
import haoc.fiap.healthbackend.repository.HandWashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HandWashService {

    @Autowired
    private HandWashRepository handWashRepository;

    public HandWashDataDto getHandWashDataFrom(Integer id) throws Exception {
        try{
            Optional<HandWashData> handWashData = handWashRepository.findById(id);

            if(handWashData.isPresent()) {
                return HandWashDataMapper.toDto(handWashData.get());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return null;
    }
}
