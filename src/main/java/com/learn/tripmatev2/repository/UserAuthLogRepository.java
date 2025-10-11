package com.learn.tripmatev2.repository;

import com.learn.tripmatev2.model.UserAuthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserAuthLogRepository extends JpaRepository<UserAuthLog, Long> {
    boolean existsByUserIdAndAuthProviderAndIpAddressAndLoginTimeGreaterThan(
        Long userId, 
        String authProvider, 
        String ipAddress, 
        java.time.LocalDateTime loginTime
    );

    List<UserAuthLog> findByUserIdOrderByLoginTimeDesc(Long userId);
}