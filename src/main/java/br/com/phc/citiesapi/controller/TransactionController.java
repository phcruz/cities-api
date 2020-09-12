package br.com.phc.citiesapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phc.citiesapi.exception.StandardError;
import br.com.phc.citiesapi.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Api of transaction")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	private Logger log = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionService transactionService;

	@ApiOperation(value = "Generate x-transaction-id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Page.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@GetMapping
	public ResponseEntity<String> generateTransactionId(HttpServletRequest request) {
		log.info("Acessanro o endpoint generateTransactionId");
		String xTransactionId =  transactionService.generateTransactionId(transactionService.getIpRequest(request));
		return ResponseEntity.ok().body(xTransactionId);
	}
}
