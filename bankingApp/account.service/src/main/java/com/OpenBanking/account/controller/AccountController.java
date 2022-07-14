package com.OpenBanking.account.controller;

import java.rmi.ServerException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OpenBanking.account.entity.Account;
import com.OpenBanking.account.repository.AccountRepository;
import com.OpenBanking.account.service.AccountService;


import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	private HttpServletRequest request;
	
	
	private static List<Account> account = new ArrayList<>();
	static {
		
		}
	
	
		
	@PostMapping("/")
	@PermitAll
	public Account saveAccount(@RequestBody Account account) {
		return accountService.saveAccount(account);
		
	}
	
	@GetMapping("/")
	@PermitAll
    public String index(){
        return "index";
    }
	
	@GetMapping("/{id}")	
	public Optional<Account> findAccountById(@PathVariable("id") Long accountId) {
		return accountService.findAccountById(accountId);
	}
	
	@GetMapping("/users/{id}")	
	public Account findAccountByUserId(@PathVariable("id") Long userId) {
		return accountService.findAccountByUserId(userId);
	}
	
	



	@GetMapping("/home")
	@RolesAllowed("admin")
	public String viewAllAccount(Model model) {
	  model.addAttribute("account",accountService.getAllAccount());
		return "home";	
	}
	
	@RolesAllowed("admin")
	@GetMapping("/findAccount/{id}")
	public String findOne(@PathVariable Long accountId, Model model){
		
		Optional<Account> account= accountService.findAccountById(accountId);	
		//if (!account.isPresent())			
		 model.addAttribute("Account", account.get());
		 
		return "home";
				}
		
	
	
	@DeleteMapping("/deleteAccount")
	@RolesAllowed("admin")
	public void deleteAccountById(int id) {
		
		Iterator<Account> iterator= account.iterator();
		while(iterator.hasNext()) {
			Account account= iterator.next();
			  if (account.getAccountId()== id) {
				  iterator.remove();
				 
			  }
		  }
		
		  }
	
	
	@PostMapping("/addAccount")
	
	@PermitAll
     public String createNewAccount(@ModelAttribute("Account") Account newAccount) {
		
		accountService.saveAccount(newAccount);
		
		return "redirect:/home";
    }
    
	
	
	
	/*public  ResponseEntity<Account> createNewAccount(@RequestBody Account newAccount) throws ServerException {
		Account account = new AccountService().saveAccount(newAccount);
		if (account == null) {
	        throw new ServerException(null);
	    } else {
	        return new ResponseEntity<>(account, HttpStatus.CREATED);
	    }
		
	}
	*/
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request ) throws ServletException {
		request.logout();
		return "redirect:/";
	}
	
	}
