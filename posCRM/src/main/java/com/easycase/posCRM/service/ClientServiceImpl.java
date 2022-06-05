package com.easycase.posCRM.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.easycase.posCRM.dao.ClientRepository;
import com.easycase.posCRM.model.Client;
import com.easycase.posCRM.util.CryptUtils;
import com.easycase.posCRM.util.IAuthenticationFacade;

@Service("clientService")
public class ClientServiceImpl extends BaseCrudService<Client, JpaRepository<Client, Long>> implements ClientService {

	private ClientRepository clientRepository;
	private static final int PAGE_SIZE = 10;

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		super(clientRepository);
		this.clientRepository = clientRepository;
	}

	public Page<Client> getList(Integer pageNumber) {
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "codeClient");
		return clientRepository.findAll(pageRequest);
	}

	public Client findByUUID(String uuid) {
		// TODO Auto-generated method stub
		return clientRepository.findByUuid(UUID.fromString(uuid));
	}

	public Client createClient(Client client) {
		System.out.println(client);
		if (client.getId() == null) {
			Authentication authentication = authenticationFacade.getAuthentication();
			Client last = clientRepository.findTopByOrderByCodeClientDesc();
			client.setUuid(UUID.randomUUID().toString());
			client.setCodeClient(last.getCodeClient() + 1);
			client.setCodeClient(clientRepository.findTopByOrderByCodeClientDesc().getCodeClient() + 1);
			client.setUser(authentication.getName());
		}
		
		if(StringUtils.isEmptyOrWhitespace(client.getAdress2()))
			client.setAdress2(null);
		
		return clientRepository.save(client);
	}

	@Transactional
	public File createConfigFile(Long id) {
		Client client = clientRepository.findById(id).get();
		Properties prop = new Properties();
		prop.setProperty("10", "localhost");
		prop.setProperty("9", CryptUtils.encrypt(client.getDateEnd()));
		prop.setProperty("8", CryptUtils.encrypt(client.getDateStart()));
		prop.setProperty("7", CryptUtils.encrypt(client.getCodeClient().toString()));
		prop.setProperty("6", CryptUtils.encrypt(client.getWebSite()));
		prop.setProperty("5", CryptUtils.encrypt(client.getEmail()));
		prop.setProperty("4", CryptUtils.encrypt(client.getTel()));
		prop.setProperty("3", CryptUtils.encrypt(client.getAdress()));
		prop.setProperty("2", CryptUtils.encrypt(client.getName()));
		prop.setProperty("1", CryptUtils.encrypt(client.getUuid()));

		File file = new File(client.getCodeClient() + " - " + client.getName() + ".properties");
		FileOutputStream outputStrem = null;
		try {
			outputStrem = new FileOutputStream(file);
			prop.store(outputStrem, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Integer i = client.getDownload() + 1;
		client.setDownload(i);
		return file;
	}

}
