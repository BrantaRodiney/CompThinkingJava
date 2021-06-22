package com.rr.Delegacao;

public class Corredor extends Atleta {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Faz Miados";
	}
	public Corredor(String nome, int idade, String dono) {
		super(nome, idade, dono);
		this.especie = "Corredor";
	}
}
