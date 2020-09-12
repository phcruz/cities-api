package br.com.phc.citiesapi.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.phc.citiesapi.exception.NotFoundException;
import br.com.phc.citiesapi.model.State;
import br.com.phc.citiesapi.repository.StateRepository;

@Service
public class StateService {

	private Logger log = LoggerFactory.getLogger(StateService.class);
	
	@Autowired
	private StateRepository stateRepository;
	
	public Page<State> findAll(Pageable page) {
		log.info("FindAll StateService");
		return stateRepository.findAll(page);
	}
	
	public State findById(Long id) {
		log.info("FindById StateService");
		Optional<State> state = stateRepository.findById(id);
		if (state.isEmpty()) {
			throw new NotFoundException("Nenhum estado encontrado com o id informado.");
		}
		return state.get();
	}
}
