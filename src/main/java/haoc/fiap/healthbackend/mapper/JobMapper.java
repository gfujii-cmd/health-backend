package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.JobDto;
import haoc.fiap.healthbackend.entity.Job;
import lombok.Data;

@Data
public class JobMapper {

    public JobDto jobToDto(Job job){
        return JobDto.builder()
                .description(job.getDescription())
                .build();
    }
}
