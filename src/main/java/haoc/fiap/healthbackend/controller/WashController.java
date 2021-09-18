package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.resquest.WashMachineInfoRequest;
import haoc.fiap.healthbackend.resquest.WashMachineRequest;
import haoc.fiap.healthbackend.service.WashMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wash")
@RequiredArgsConstructor
public class WashController {

    private final WashMachineService washMachineService;

    @PostMapping("/set-info")
    public ResponseEntity<BaseResponse<WashMachineDto>> setWashMachineData(
            @RequestBody WashMachineInfoRequest request) throws Exception {
        WashMachineDto dto = washMachineService.setWashMachineInfo(request);

        return ResponseEntity.ok(BaseResponse.<WashMachineDto>builder()
                        .httpCode(200)
                        .message("Cadastrado com sucesso")
                        .response(dto)
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<WashMachineDto>> createWashMachine(@RequestBody WashMachineRequest request)
            throws Exception {
        WashMachineDto dto = washMachineService.createWashMachine(request.getLocation());

        return ResponseEntity.ok(BaseResponse.<WashMachineDto>builder()
                        .message("Maquina cadastrada com sucesso")
                        .httpCode(200)
                        .response(dto)
                .build());
    }
}
