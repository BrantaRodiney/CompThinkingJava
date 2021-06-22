package com.rr.Delegacao;

public class Corredor extends Atleta {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Correr";
	}
	public Corredor(String nome, int numero, String velocidade) {
		super(nome, numero, velocidade);
		this.atleta = "Corredor";
	}
}
