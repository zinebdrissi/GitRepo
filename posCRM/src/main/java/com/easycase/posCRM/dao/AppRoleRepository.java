package com.easycase.posCRM.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easycase.posCRM.model.AppRole;


@Repository("roleRepository")
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	
}

