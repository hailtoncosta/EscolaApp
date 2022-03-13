package com.app.minhaescolaapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Aluno aluno;
	
	private String disciplina;
	private Integer bimestre1;
	private Integer bimestre2;
	private Integer bimestre3;
	private Integer bimestre4;
	private Integer total;
	private Double media;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Integer getBimestre1() {
		return bimestre1;
	}
	public void setBimestre1(Integer bimestre1) {
		this.bimestre1 = bimestre1;
	}
	public Integer getBimestre2() {
		return bimestre2;
	}
	public void setBimestre2(Integer bimestre2) {
		this.bimestre2 = bimestre2;
	}
	public Integer getBimestre3() {
		return bimestre3;
	}
	public void setBimestre3(Integer bimestre3) {
		this.bimestre3 = bimestre3;
	}
	public Integer getBimestre4() {
		return bimestre4;
	}
	public void setBimestre4(Integer bimestre4) {
		this.bimestre4 = bimestre4;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Double getMedia() {
		return media;
	}
	public void setMedia(Double media) {
		this.media = media;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	
}
