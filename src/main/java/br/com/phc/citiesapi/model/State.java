package br.com.phc.citiesapi.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "State")
@Table(name = "estado")
public class State {

	@Id
	private Long id;
	@Column(name = "nome")
	private String name;
	private String uf;
	private Integer ibge;
	@ManyToOne
	@JoinColumn(name = "pais", referencedColumnName = "id")
	private Country country;
	private String ddd;

	public State() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUf() {
		return uf;
	}

	public Integer getIbge() {
		return ibge;
	}

	public List<Integer> getDdd() {
		return Arrays.asList(ddd.split(","))
				.stream()
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	public Country getCountry() {
		return country;
	}

}
