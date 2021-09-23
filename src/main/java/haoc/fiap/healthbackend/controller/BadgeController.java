package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.BadgeDto;
import haoc.fiap.healthbackend.entity.Badge;
import haoc.fiap.healthbackend.mapper.BadgeMapper;
import haoc.fiap.healthbackend.response.BaseListResponse;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("badge")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping("/all")
    public ResponseEntity<BaseListResponse<BadgeDto>> getAllBadges() {
        List<Badge> badgeList = badgeService.getAllBadges();
        return ResponseEntity.ok(BaseListResponse.<BadgeDto>builder()
                .response(BadgeMapper.badgeDtoToDtoList(badgeList))
                .httpCode(200)
                .message("Todas as insignias retornadas com sucesso")
                .build());
    }

    @PostMapping("user/{userId}/upgrade")
    public ResponseEntity<BaseResponse<BadgeDto>> setNewBadge(@PathVariable("userId") Integer id)
            throws Exception{
        return ResponseEntity.ok(BaseResponse.<BadgeDto>builder()
                        .message("OK")
                        .response(badgeService.setNewBadge(id))
                        .httpCode(200)
                .build());
    }
}
