package com.rr.Delegacao;

import java.io.Serializable;

public abstract class Atleta implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String nome;
	private   int numero;
	protected String atleta;
	
	public Atleta(String nome, int idade, String dono) {
		this.nome = nome;
		this.numero = idade;
		this.atleta = atleta;
	}
	public String toString() {
		String retorno = "";
		retorno += "Nome: "     + this.nome     + "\n";
		retorno += "Número: "    + this.numero    + "\n";
		retorno += "Atleta: "  + this.atleta  + "\n";
		retorno += "Função: "  + soar()        + "\n";
		return retorno;
	}
	public abstract String soar();
}
