package com.easycase.posCRM.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.easycase.posCRM.model.Client;


@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("SELECT c FROM Client c where c.uuid = :param")
	Client findByUuid(@Param("param") UUID param);
	Client findTopByOrderByCodeClientDesc();
}

