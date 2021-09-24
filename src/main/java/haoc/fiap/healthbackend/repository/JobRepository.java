package haoc.fiap.healthbackend.repository;

import haoc.fiap.healthbackend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
    Job findByName(String name);
}
