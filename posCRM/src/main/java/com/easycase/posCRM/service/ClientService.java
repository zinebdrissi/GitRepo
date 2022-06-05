package com.easycase.posCRM.service;

import java.io.File;

import org.springframework.data.domain.Page;

import com.easycase.posCRM.model.Client;

public interface ClientService extends IBaseService<Client> {

	Page<Client> getList(Integer pageNumber);
	Client createClient(Client client);
	Client findByUUID(String uuid);
	
	File createConfigFile(Long id);

}
