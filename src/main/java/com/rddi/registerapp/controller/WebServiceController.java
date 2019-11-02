package com.rddi.registerapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rddi.registerapp.dto.CommentWebServiceRequest;
import com.rddi.registerapp.dto.RateWebServiceRequest;
import com.rddi.registerapp.dto.ValidateContractResponse;
import com.rddi.registerapp.dto.WebServiceCommentResponse;
import com.rddi.registerapp.form.ClientSdkGenerationForm;
import com.rddi.registerapp.form.ServerStubGenerationForm;
import com.rddi.registerapp.model.WebServiceComment;
import com.rddi.registerapp.service.WebServiceManagement;

@Controller
@RequestMapping(value = "/ws")
public class WebServiceController {
	
	Logger logger = LoggerFactory.getLogger(WebServiceController.class);
	
	@Autowired
	private WebServiceManagement webServiceManagement;
	
	@GetMapping(value = "/validate")
	@ResponseBody
	public ValidateContractResponse getWebServiceComments(@RequestParam("contractUrl") String contractUrl) {
		ValidateContractResponse response = webServiceManagement.validateWebServiceContract(contractUrl);
		
		return response;
	}
	
	@GetMapping(value = "/comments")
	@ResponseBody
	public List<WebServiceCommentResponse> getWebServiceComments(@RequestParam("webServiceId") Long webServiceId) {
		List<WebServiceComment> comments = webServiceManagement.getWebServiceComments(webServiceId);
		
		List<WebServiceCommentResponse> response = new ArrayList<>();
		comments.forEach(comment -> {
			WebServiceCommentResponse commentDto = new WebServiceCommentResponse();
			BeanUtils.copyProperties(comment, commentDto);
			response.add(commentDto);
		});
		
		return response;
	}
	
	@PostMapping(value = "/rate")
	@ResponseBody
	public void rateWebService(@RequestBody RateWebServiceRequest rateWebServiceRequest) {
		webServiceManagement.rateWebService(rateWebServiceRequest.getWebServiceId(), rateWebServiceRequest.getRating());
	}
	
	@PostMapping(value = "/comment")
	@ResponseBody
	public void commentWebService(@RequestBody CommentWebServiceRequest commentWebServiceRequest) {
		webServiceManagement.commentWebService(commentWebServiceRequest.getWebServiceId(),
				commentWebServiceRequest.getAuthor(), commentWebServiceRequest.getComment());
	}
	
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
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException ex) {
		logger.error(ex.getMessage(), ex);
		return "error";
	}
}
