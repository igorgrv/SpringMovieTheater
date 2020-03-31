package com.movieTheater.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author igorg
 */
@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private BigDecimal price = BigDecimal.ZERO;;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Lugar> lugar = new HashSet<>();

	/**
	 * @deprecated hibernate only
	 */
	public Room() {

	}

	// Constructor
	// -----------------------------------------------------------------
	public Room(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	// -----------------------------------------------------------------
	// Methods
	public void add(Lugar lugar) {
		this.lugar.add(lugar);
	}

	public Map<String, List<Lugar>> getMapaDeLugares() {
		if (!this.lugar.isEmpty()) {
			return this.lugar.stream().collect(Collectors.groupingBy(Lugar::getFileira, Collectors.toList()));
		}
		return Collections.emptyMap();
	}

	public Integer lugar(String fileira, Integer posicao) {
		Optional<Lugar> optional = this.lugar.stream()
				.filter((x) -> fileira.equals(x.getFileira()) && posicao.equals(x.getPosicao())).findFirst();
		return optional.get().getId();
	}

	// -----------------------------------------------------------------
	// Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Lugar> getSeat() {
		return lugar;
	}

	public void setSeat(Set<Lugar> lugar) {
		this.lugar = lugar;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.UP);
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
