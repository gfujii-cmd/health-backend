package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.entity.Badge;
import haoc.fiap.healthbackend.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    public List<Badge> getAllBadges(){
        return badgeRepository.findAll();
    }
}
