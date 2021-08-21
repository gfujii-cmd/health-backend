package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.JobDto;
import haoc.fiap.healthbackend.entity.Job;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class JobMapper {

    public static JobDto jobToDto(Job job){
        return JobDto.builder()
                .name(job.getName())
                .build();
    }

    public static List<JobDto> jobToDtoList(List<Job> jobs){
        return jobs.stream()
                .map(JobMapper::jobToDto)
                .collect(Collectors.toList());
    }
}
