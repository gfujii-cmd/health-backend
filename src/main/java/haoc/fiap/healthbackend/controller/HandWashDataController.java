package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.HandWashDataDto;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.service.HandWashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/hand-wash-data")
@RequiredArgsConstructor
public class HandWashDataController {

    private final HandWashService handWashService;

    @GetMapping("{email}")
    public ResponseEntity<BaseResponse<HandWashDataDto>> getWashData(@PathVariable("email") String email)
            throws Exception{

        return ResponseEntity.ok(BaseResponse.<HandWashDataDto>builder()
                        .httpCode(200)
                        .response(handWashService.getHandWashDataFrom(email))
                        .message("Dados retornados com sucesso")
                .build());
    }
}
