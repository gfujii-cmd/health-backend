package haoc.fiap.healthbackend.mapper;

import haoc.fiap.healthbackend.dto.JobDto;
import haoc.fiap.healthbackend.entity.Job;
import haoc.fiap.healthbackend.resquest.JobRequest;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class JobMapper {

    public static JobDto jobToDto(Job job){
        return JobDto.builder()
                .jobId(job.getId())
                .name(job.getName())
                .build();
    }

    public static List<JobDto> jobToDtoList(List<Job> jobs){
        return jobs.stream()
                .map(JobMapper::jobToDto)
                .collect(Collectors.toList());
    }

    public static Job toJob(JobRequest request){
        return Job.builder()
                .name(request.getName())
                .build();
    }
}
