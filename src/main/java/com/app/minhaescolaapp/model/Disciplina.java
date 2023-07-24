package com.app.minhaescolaapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "disciplina")
@SequenceGenerator(name = "seq_disciplina", sequenceName = "seq_disciplina", allocationSize = 1, initialValue = 1)
public class Disciplina implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String discricaoDisciplina;
	
	public Disciplina() {
		
	}

	public Disciplina(Long id, String discricaoDisciplina) {
		super();
		this.id = id;
		this.discricaoDisciplina = discricaoDisciplina;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiscricaoDisciplina() {
		return discricaoDisciplina;
	}

	public void setDiscricaoDisciplina(String discricaoDisciplina) {
		this.discricaoDisciplina = discricaoDisciplina;
	}

}
