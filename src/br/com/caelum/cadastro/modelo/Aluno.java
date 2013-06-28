package br.com.caelum.cadastro.modelo;

import java.io.Serializable;

public class Aluno implements Serializable{
	private static final long serialVersionUID = 1308345071673162122L;
	private Long id;
	private String nome;
	private String site;
	private String endereco;
	private String telefone;
	private Double nota;
	private String localDaFoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getLocalDaFoto() {
		return localDaFoto;
	}

	public void setLocalDaFoto(String localDaFoto) {
		this.localDaFoto = localDaFoto;
	}

	@Override
	public String toString() {
		return nome;
	}
	
}
