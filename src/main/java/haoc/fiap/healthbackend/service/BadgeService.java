package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.BadgeDto;
import haoc.fiap.healthbackend.entity.Badge;
import haoc.fiap.healthbackend.entity.User;
import haoc.fiap.healthbackend.mapper.BadgeMapper;
import haoc.fiap.healthbackend.repository.BadgeRepository;
import haoc.fiap.healthbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Badge> getAllBadges(){
        return badgeRepository.findAll();
    }

    public BadgeDto setNewBadge(Integer id) throws Exception{
        try{
           Optional<User> user = userRepository.findById(id);

           if(user.isPresent()) {
               if(isScoreEnough(user.get())){
                   user.get().getBadge().setLevel(user.get().getBadge().getLevel() + 1);
                   userRepository.save(user.get());
                   return BadgeMapper.badgeToDto(user.get().getBadge());
               }
           }
           throw new Exception("Usuario não encontrado");

        } catch (Exception e){
            throw new Exception(e.getMessage(), e);
        }
    }

    private boolean isScoreEnough(User user) {
        boolean isEnough;
        switch (user.getBadge().getLevel()) {
            case 1:
                isEnough = user.getScore() > 1000;
            case 2:
                isEnough = user.getScore() > 2000;
                break;
            case 3:
                isEnough = user.getScore() > 3000;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getBadge().getLevel());
        }
        return isEnough;
    }
}
