package haoc.fiap.healthbackend.repository;

import haoc.fiap.healthbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    Optional<User> findByRfid(String rfid);

    @Transactional(readOnly = true)
    @Query(value = "SELECT SCORE FROM USERS "
            + "WHERE EMAIL = :email", nativeQuery = true)
    Integer getUserScore(String email);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM USERS " +
            "ORDER BY SCORE DESC " +
            "LIMIT 3", nativeQuery = true)
    List<User> getTopList();
}