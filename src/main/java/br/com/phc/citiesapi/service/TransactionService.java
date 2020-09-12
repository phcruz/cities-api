package br.com.phc.citiesapi.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	private Logger log = LoggerFactory.getLogger(TransactionService.class);
	
	public String generateTransactionId(String ipUser) {
		log.info("Generate transaction id in TransactionService");
		String gerarTransaction = ipUser + ":web-application:" + this.getDateTime();
		return Base64.getUrlEncoder().encodeToString(gerarTransaction.getBytes());
	}
	
	public String getIpRequest(HttpServletRequest request) {
		log.info("Get address ip user in TransactionService");
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null) {
			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
			    ipAddress = request.getHeader("X_FORWARDED_FOR");
			    if (ipAddress == null){
			        ipAddress = request.getRemoteAddr();
			    }
			}
		}
		return ipAddress;
	}
	
	public String getDateTime() {
		log.info("Generate date valid in TransactionService");
        return LocalDateTime.now().plusMinutes(10).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}
