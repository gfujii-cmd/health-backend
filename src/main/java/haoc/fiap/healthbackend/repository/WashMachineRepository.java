package haoc.fiap.healthbackend.repository;

import haoc.fiap.healthbackend.entity.WashMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WashMachineRepository extends JpaRepository<WashMachine, Integer> {
}
