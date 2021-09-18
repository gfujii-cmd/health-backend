package haoc.fiap.healthbackend.repository;

import haoc.fiap.healthbackend.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {
}
