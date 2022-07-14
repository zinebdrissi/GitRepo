package com.OpenBanking.account.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OpenBanking.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	
	Optional<Account> findByAccountId(Long accountId);
	Account findByUserId(Long userId);
		
	
}
