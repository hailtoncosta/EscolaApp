package com.app.minhaescolaapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "secretario")
public class Secretario extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_professor")
	private Long id;
	
	public Secretario(String nome, String cpf, String telefone, String email, String endereco, String foto) {
		super(nome, cpf, telefone, email, endereco, foto);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
