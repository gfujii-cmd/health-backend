package haoc.fiap.healthbackend.repository;

import haoc.fiap.healthbackend.entity.WashMachine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WashMachineRepository extends JpaRepository<WashMachine, Integer> {
    Optional<WashMachine> findByMacAddress(String macAddress);
}
