package br.com.phc.citiesapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phc.citiesapi.exception.StandardError;
import br.com.phc.citiesapi.model.State;
import br.com.phc.citiesapi.service.StateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Api of search states")
@RestController
@RequestMapping("/states")
public class StateController {

	private Logger log = LoggerFactory.getLogger(StateController.class);
	
	@Autowired
	private StateService stateService; 

	@ApiOperation(value = "Find all states")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Page.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class)})
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@GetMapping
	public Page<State> findAll(Pageable page) {
		log.info("Access findAll state");
		return stateService.findAll(page);
	}
	
	@ApiOperation(value = "Find state by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = State.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class)})
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@GetMapping("/{id}")
	public ResponseEntity<State> getOne(@PathVariable("id") Long id) {
		log.info("Access getOne state");
		return ResponseEntity.ok().body(stateService.findById(id));
	}
}
