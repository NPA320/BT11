package BT11.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BT11.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    
    Optional<UserInfo> findByName(String username);
}
