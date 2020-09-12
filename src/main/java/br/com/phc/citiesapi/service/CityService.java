package br.com.phc.citiesapi.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.phc.citiesapi.exception.NotFoundException;
import br.com.phc.citiesapi.model.City;
import br.com.phc.citiesapi.repository.CityRepository;

@Service
public class CityService {
	
	private Logger log = LoggerFactory.getLogger(CityService.class);
	
	@Autowired
	private CityRepository cityRepository;
	
	public Page<City> findAll(Pageable page) {
		log.info("FindAll in CityService");
		return cityRepository.findAll(page);
	}
	
	public City findById(Long id) {
		log.info("FindById in CityService");
		Optional<City> city = cityRepository.findById(id);
		if (city.isEmpty()) {
			throw new NotFoundException("Nenhuma cidade encontrada com o id informado.");
		}
		return city.get();
	}
}
