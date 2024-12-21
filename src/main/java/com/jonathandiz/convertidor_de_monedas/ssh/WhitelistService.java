package com.jonathandiz.convertidor_de_monedas.ssh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonathandiz.convertidor_de_monedas.config.AppProperties;

@Service
public class WhitelistService {

	private final AppProperties appProperties;
	
	@Autowired
	public WhitelistService(AppProperties appProperties) {
		this.appProperties = appProperties;
	}
	
	public boolean isAllowed(String ip) {
		return appProperties.getWhitelist().contains(ip);
	}
}
