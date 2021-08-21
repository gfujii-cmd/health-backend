package haoc.fiap.healthbackend.controller;

import haoc.fiap.healthbackend.dto.JobDto;
import haoc.fiap.healthbackend.entity.Job;
import haoc.fiap.healthbackend.mapper.JobMapper;
import haoc.fiap.healthbackend.response.BaseListResponse;
import haoc.fiap.healthbackend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("job")
public class JobController {

    private final JobService jobService;

    private static JobMapper mapper;

   @GetMapping("/all")
    public ResponseEntity<BaseListResponse<JobDto>> getAllJobs(){
        List<Job> jobList = this.jobService.getAllJobs();
        return ResponseEntity.ok(BaseListResponse.<JobDto>builder()
                .data(mapper.jobToDtoList(jobList))
                .httpCode(200)
                .error(null)
                .build());
   }
}
