package com.idontchop.dateprofileservice.controllers;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idontchop.dateprofileservice.dtos.ReduceRequest;
import com.idontchop.dateprofileservice.dtos.RestMessage;
import com.idontchop.dateprofileservice.repositories.ProfileRepository;

/**
 * /reduce/
 * 
 * Will reduce a potentials list by traits with selection
 * 
 * @author micro
 *
 */
@RestController
public class MainController {
		
	@Value ("${server.port}")
	private String serverPort;
	
	@Value("${spring.application.name}")
	private String appName;
	
	/**
	 * Reduces a request based on the selections on traits.
	 * 
	 * For example, if the user doesn't want to see smokers.
	 * 
	 * @param reduceRequest
	 * @return
	 */
	@GetMapping(value = "/reduce")
	public List<String> reduce(@RequestBody @Valid ReduceRequest reduceRequest) {
		return null;
	}
	
	
	@GetMapping("/helloWorld")
	public RestMessage helloWorld () {
		String serverAddress,serverHost;
		try {
			serverAddress = NetworkInterface.getNetworkInterfaces().nextElement()
					.getInetAddresses().nextElement().getHostAddress();
		} catch (SocketException e) {
			serverAddress = e.getMessage();
		}
		try {
			serverHost = NetworkInterface.getNetworkInterfaces().nextElement()
					.getInetAddresses().nextElement().getHostName();
		} catch (SocketException e) {
			serverHost = e.getMessage();
		}
		return RestMessage.build("Hello from " + appName)
				.add("service", appName)
				.add("host", serverHost)
				.add("address", serverAddress)
				.add("port", serverPort);
			
	}
		

}
