package com.app.minhaescolaapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "professor")
@SequenceGenerator(name = "seq_professor", sequenceName = "seq_professor", allocationSize = 1, initialValue = 1)
public class Professor extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_professor")
	private Long id;
	
	public Professor() {
	}
	
	
	public Professor(String nome, String cpf, String telefone, String email, String endereco, String foto, Long id) {
		super(nome, cpf, telefone, email, endereco, foto);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	


}
