package com.easycase.posCRM.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.easycase.posCRM.model.AppUser;
import com.easycase.posCRM.model.Client;

@Repository("userRepository")
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	@Query("SELECT u FROM AppUser u where u.userName = :param")
	AppUser findByUserName(@Param("param") String param);

}
