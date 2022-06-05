package com.easycase.posCRM.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easycase.posCRM.dao.AppRoleRepository;
import com.easycase.posCRM.dao.AppUserRepository;
import com.easycase.posCRM.dao.UserRoleRepository;
import com.easycase.posCRM.model.AppUser;
import com.easycase.posCRM.model.UserRole;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	 	@Autowired
	    private AppUserRepository userRepository;
	 
	    @Autowired
	    private AppRoleRepository roleRepository;
	    
	    @Autowired
	    private UserRoleRepository userRoleRepository;
	 
	   
	    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	        AppUser appUser = userRepository.findByUserName(userName);
	        if (appUser == null) {
	            System.out.println("User not found! " + userName);
	            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
	        }
	        System.out.println("Found User: " + appUser);
	 
	        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
	        if (appUser.getUserRoles() != null) {
	            for (UserRole role : appUser.getUserRoles() ) {
	                // ROLE_USER, ROLE_ADMIN,..
	                GrantedAuthority authority = new SimpleGrantedAuthority(role.getAppRole().getRoleName());
	                grantList.add(authority);
	            }
	        }
	 
	        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
	                appUser.getEncrytedPassword(), grantList);
	 
	        return userDetails;
	    }

}
