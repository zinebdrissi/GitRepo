package com.easycase.posCRM.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table(name = "User_Role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "User_Id", "Role_Id" }) })
public class UserRole {
 
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "User_Id", nullable = false)
    private AppUser appUser;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Role_Id", nullable = false)
    private AppRole appRole;

	@Override
	public String toString() {
		return appUser.toString();
	}
}