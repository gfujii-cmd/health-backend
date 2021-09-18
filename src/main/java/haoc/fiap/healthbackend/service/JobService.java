package haoc.fiap.healthbackend.service;

import haoc.fiap.healthbackend.entity.Job;
import haoc.fiap.healthbackend.repository.JobRepository;
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

}
