package BT11.repository;

import java.util.Optional;

import BT11.entity.*;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
@Query("SELECT u FROM Role u WHERE u.name = :name")
public Role getUserByName(@Param("name") String name);
Optional<Role> findByName(String name);
}
