package com.app.minhaescolaapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
@SequenceGenerator(name = "seq_sala", sequenceName = "seq_sala", allocationSize = 1, initialValue = 1)
public class Sala implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricaoSala;
	
	public Sala() {
		
	}

	public Sala(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoSala() {
		return descricaoSala;
	}

	public void setDescricaoSala(String descricaoSala) {
		this.descricaoSala = descricaoSala;
	}
	
}
