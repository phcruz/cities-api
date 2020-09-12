package br.com.phc.citiesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phc.citiesapi.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

}
