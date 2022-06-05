package com.easycase.posCRM.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String uuid;
	private String subtitle;
	private String name;
	private String name2;
	private String adress;
	private String adress2;
	private String tel;
	private String email;
	private String webSite;
	private Long codeClient;
	private String dateStart;
	private String dateEnd;
	private String line1;
	private String line2;
	private String line3;
	private String line4;
	private Double price = 0d;
	private Integer download = 0;
	private String user;


	@Version
	@Column(name = "version")
	private int version;

}
