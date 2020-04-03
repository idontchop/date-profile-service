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
import com.idontchop.dateprofileservice.dtos.TraitSelectionPair;
import com.idontchop.dateprofileservice.repositories.ProfileRepository;
import com.idontchop.dateprofileservice.services.ProfileService;

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
	
	@Autowired
	ProfileService profileService;
	
	/**
	 * Reduces a request based on the selections on traits.
	 * 
	 * For example, if the user doesn't want to see smokers.
	 * 
	 * See /sampleReduceRequest for example of expected json requestbody
	 * 
	 * @param reduceRequest
	 * @return
	 */
	@GetMapping(value = "/reduce")
	public List<String> reduce(@RequestBody @Valid ReduceRequest reduceRequest) {
		return profileService.reduceByTraitSelections(reduceRequest.getPotentials(),
				reduceRequest.getSelections() );
	}
	
	/**
	 * development to see json of reducerequest
	 * 
	 * @return
	 */
	@GetMapping(value = "/sampleReduceRequest")
	public ReduceRequest reduceJson () {
		ReduceRequest reduceRequest = new ReduceRequest();
		TraitSelectionPair pair = new TraitSelectionPair("smoking","packs");
		reduceRequest.setPotentials(List.of("1","2"));
		reduceRequest.setSelections(List.of(pair));

		return reduceRequest;
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
