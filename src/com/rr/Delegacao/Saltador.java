package com.rr.Delegacao;

public class Saltador extends Atleta {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Faz latidos";
	}
	public Saltador(String nome, int idade, String dono) {
		super(nome, idade, dono);
		this.especie = "Cachorro";
	}
}
