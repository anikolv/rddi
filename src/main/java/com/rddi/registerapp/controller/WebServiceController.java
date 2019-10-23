package com.rddi.registerapp.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rddi.registerapp.form.ClientSdkGenerationForm;
import com.rddi.registerapp.form.ServerStubGenerationForm;
import com.rddi.registerapp.service.WebServiceManagement;

@Controller
public class WebServiceController {
	
	@Autowired
	private WebServiceManagement webServiceManagement;
	
	@PostMapping(value = "/client/generate/{webServiceId}")
	public void generateClient(
			@PathVariable("webServiceId") Long webServiceId,
			@ModelAttribute(value="clientSdkGenerationForm") ClientSdkGenerationForm clientSdkGenerationForm,
			HttpServletResponse response) throws IOException {
		byte[] downloadedBytes = webServiceManagement.generateClient(webServiceId, clientSdkGenerationForm.getLanguage());
		ServletOutputStream outStream = response.getOutputStream();
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=" + clientSdkGenerationForm.getLanguage() + "-client-generated.zip");
		outStream.write(downloadedBytes);
		outStream.flush();
	}
	
	@PostMapping(value = "/server/generate/{webServiceId}")
	public void generateServer(
			@PathVariable("webServiceId") Long webServiceId,
			@ModelAttribute(value="serverStubGenerationForm") ServerStubGenerationForm serverStubGenerationForm,
			HttpServletResponse response) throws IOException {
		byte[] downloadedBytes = webServiceManagement.generateServer(webServiceId, serverStubGenerationForm.getFramework());
		ServletOutputStream outStream = response.getOutputStream();
		response.setContentType("application/zip");
		response.setHeader("Content-Disposition", "attachment; filename=" + serverStubGenerationForm.getFramework() + "-server-generated.zip");
		outStream.write(downloadedBytes);
		outStream.flush();
	}
}
