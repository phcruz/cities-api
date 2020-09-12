package br.com.phc.citiesapi.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.phc.citiesapi.exception.NotFoundException;
import br.com.phc.citiesapi.model.Country;
import br.com.phc.citiesapi.repository.CountryRepository;

@Service
public class CountryService {

	private Logger log = LoggerFactory.getLogger(CountryService.class);
	
	@Autowired
	private CountryRepository countryRepository;
	
	public Page<Country> findAll(Pageable page) {
		log.info("FindAll CountryService");
		return countryRepository.findAll(page);
	}
	
	public Country findById(Long id) {
		log.info("FindById CountryService");
		Optional<Country> country = countryRepository.findById(id);
		if (country.isEmpty()) {
			throw new NotFoundException("Nenhum país encontrado com o id informado.");
		}
		return country.get();
	}
}
