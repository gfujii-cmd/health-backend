package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.WashMachineDto;
import haoc.fiap.healthbackend.response.BaseResponse;
import haoc.fiap.healthbackend.resquest.WashMachineInfoRequest;
import haoc.fiap.healthbackend.service.WashMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wash")
@RequiredArgsConstructor
public class WashController {

    private final WashMachineService washMachineService;

//    @PostMapping("/set-info")
//    public ResponseEntity<BaseResponse<WashMachineDto>> setWashMachineData(
//            @RequestBody WashMachineInfoRequest request) throws Exception {
//
//
//    }
}
