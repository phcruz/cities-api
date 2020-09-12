package br.com.phc.citiesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.citiesapi.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}