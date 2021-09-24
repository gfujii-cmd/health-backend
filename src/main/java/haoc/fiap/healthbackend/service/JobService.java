package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.dto.JobDto;
import haoc.fiap.healthbackend.entity.Job;
import haoc.fiap.healthbackend.mapper.JobMapper;
import haoc.fiap.healthbackend.repository.JobRepository;
import haoc.fiap.healthbackend.resquest.JobRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs(){
       return jobRepository.findAll();
    }

    public JobDto newJob(JobRequest request){
        return JobMapper.jobToDto(jobRepository.save(JobMapper.toJob(request)));
    }

}
