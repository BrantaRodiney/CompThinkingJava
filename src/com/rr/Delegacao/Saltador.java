package com.rr.Delegacao;

public class Saltador extends Atleta {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Realizar saltos";
	}
	public Saltador(String nome, int idade, double altura) {
		super(nome, idade, altura);
		this.atleta = "Saltador";
	}
}
