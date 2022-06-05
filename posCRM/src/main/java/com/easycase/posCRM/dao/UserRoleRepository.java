package com.easycase.posCRM.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easycase.posCRM.model.UserRole;

@Repository("userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
