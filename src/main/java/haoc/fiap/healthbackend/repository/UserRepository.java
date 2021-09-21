package haoc.fiap.healthbackend.repository;

import haoc.fiap.healthbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Transactional(readOnly = true)
    @Query(value = "SELECT SCORE FROM USERS "
            + "WHERE USER_ID = :id", nativeQuery = true)
    Integer getUserScore(Integer id);
}