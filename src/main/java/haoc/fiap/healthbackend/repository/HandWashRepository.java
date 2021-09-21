package haoc.fiap.healthbackend.repository;

import haoc.fiap.healthbackend.entity.HandWashData;
import haoc.fiap.healthbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandWashRepository extends JpaRepository<HandWashData, Integer> {
}
