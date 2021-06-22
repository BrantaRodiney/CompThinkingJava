package com.rr.Delegacao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Delegacao {
	private ArrayList<Atleta> atletas;

	public Delegacao() {
		this.atletas = new ArrayList<Atleta>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Corredor leGato (){


		String [] nomeVal = {"Nome", "Idade", "Dono"};
		String[] valores = leValores (nomeVal);


		int idade = this.retornaInteiro(valores[1]);

		Corredor corredor = new Corredor(valores[0],idade,valores[2]);
		return corredor;
	}

	public Saltador leCao (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Idade", "Dono"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Saltador saltador = new Saltador(valores[0],idade,valores[2]);
		return saltador;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // M�todo est�tico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // N�o conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto n�o for poss�vel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um n�mero inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaMamiferos (ArrayList<Atleta> atletas){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\petStore.dados"));
			for (int i = 0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Imposs�vel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Atleta> recuperaMamiferos (){
		ArrayList<Atleta> mamiferosTemp = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\petStore.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					mamiferosTemp.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com mam�feros N�O existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return mamiferosTemp;
		}
	}

	public void menuPetStore (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Delegacao\n" +
					"Op��es:\n" + 
					"1. Entrar Mam�feros\n" +
					"2. Exibir Mam�feros\n" +
					"3. Limpar Mam�feros\n" +
					"4. Gravar Mam�feros\n" +
					"5. Recuperar Mam�feros\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Animais Mam�feros\n" +
						"Op��es:\n" + 
						"1. C�o\n" +
						"2. Corredor\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: atletas.add((Atleta)leCao());
				break;
				case 2: atletas.add((Atleta)leGato());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Animal mam�fero para entrada N�O escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com animais mam�feros primeiramente");
					break;
				}
				String dados = "";
				for (int i = 0; i < atletas.size(); i++)	{
					dados += atletas.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com animais mam�feros primeiramente");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com animais mam�feros primeiramente");
					break;
				}
				salvaMamiferos(atletas);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				atletas = recuperaMamiferos();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo PETSTORE");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Delegacao pet = new Delegacao();
		pet.menuPetStore();

	}

}
