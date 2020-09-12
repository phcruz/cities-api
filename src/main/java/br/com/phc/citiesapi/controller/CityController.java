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
import br.com.phc.citiesapi.model.City;
import br.com.phc.citiesapi.model.State;
import br.com.phc.citiesapi.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Api of search cities")
@RestController
@RequestMapping("/cities")
public class CityController {

	private Logger log = LoggerFactory.getLogger(CityController.class);
	
	@Autowired
	private CityService cityService;

	@ApiOperation(value = "Find all cities")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = Page.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@GetMapping
	public Page<City> findAll(Pageable page) {
		log.info("Access findAll city");
		return cityService.findAll(page);
	}

	@ApiOperation(value = "Find city by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK", response = State.class),
			@ApiResponse(code = 400, message = "Bad request", response = StandardError.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
			@ApiResponse(code = 403, message = "Forbidden", response = StandardError.class),
			@ApiResponse(code = 404, message = "Not found", response = StandardError.class),
			@ApiResponse(code = 500, message = "Internal server error", response = StandardError.class) })
	@ApiImplicitParams({@ApiImplicitParam(name = "x-transaction-id", paramType = "header",
			defaultValue = "MDowOjA6MDowOjA6MDoxOndlYi1hcHBsaWNhdGlvbjoxMi8wOS8yMDIwIDE4OjE3OjA4", required = true) })
	@GetMapping("/{id}")
	public ResponseEntity<City> getOne(@PathVariable("id") Long id) {
		log.info("Access getOne city");
		return ResponseEntity.ok().body(cityService.findById(id));
	}
}
