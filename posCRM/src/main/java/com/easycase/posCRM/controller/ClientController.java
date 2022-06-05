package com.easycase.posCRM.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easycase.posCRM.model.Client;
import com.easycase.posCRM.service.ClientService;
import com.easycase.posCRM.util.WebUtils;

@Controller
@RequestMapping("clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping
	public String index(Model model, Principal principal) {
		
	    String userName = principal.getName();
		System.out.println("User Name: " + userName);
		 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        System.out.println(userInfo);
        model.addAttribute("userInfo", userInfo);
        
        return "redirect:/clients/1";
	}
	@PreAuthorize("hasAuthority('CLIENT_LIST')")
	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Client> page = clientService.getList(pageNumber);
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		model.addAttribute("list", page);
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		return "clients/list";

	}
	@PreAuthorize("hasAuthority('CLIENT_ADD')")
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("client", new Client());
		return "clients/form";

	}
	@PreAuthorize("hasAuthority('CLIENT_EDIT')")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("client", clientService.find(id));
		return "clients/form";
	}

	@PostMapping(value = "/save")
	public String save(Client client, final RedirectAttributes ra) {
		Client save = clientService.createClient(client);
		ra.addFlashAttribute("successFlash", "Client Ajouter avec succees.");
		return "redirect:/clients";

	}
	@PreAuthorize("hasAuthority('CLIENT_DELETE')")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {

		clientService.delete(id);
		return "redirect:/clients";

	}
	@PreAuthorize("hasAuthority('CLIENT_DOWNLOAD_CONFIG')")
	@RequestMapping("/download/{id}")
	public String downloadPDFResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {

		File file = clientService.createConfigFile(id);
		if (file.exists()) {
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				FileCopyUtils.copy(inputStream, response.getOutputStream());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return "redirect:/clients";

	}
}
