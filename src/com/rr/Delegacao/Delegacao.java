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

	public Corredor leCorredores (){


		String [] nomeVal = {"Nome", "Número", "Velocidade"};
		String[] valores = leValores (nomeVal);


		int numero = this.retornaInteiro(valores[1]);

		Corredor corredor = new Corredor(valores[0],numero,valores[2]);
		return corredor;
	}

	public Saltador leSaltador (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Número", "Altura"};
		valores = leValores (nomeVal);

		int numero = this.retornaInteiro(valores[1]);

		Saltador saltador = new Saltador(valores[0],numero,valores[2]);
		return saltador;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public int retornaInteiro(String entrada) {
		int numInt;

		//Enquanto n�o for poss�vel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaAatletas (ArrayList<Atleta> atletas){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\petStore.dados"));
			for (int i = 0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossivel criar arquivo!");
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
	public ArrayList<Atleta> recuperaAtletas (){
		ArrayList<Atleta> atletas1 = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\petStore.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					atletas1.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) {
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com atletas não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return atletas1;
		}
	}

	public void menuPetStore (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Delegacao\n" +
					"Opções: \n" +
					"1. Entrar com Atletas\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atletas\n" +
					"4. Gravar Atletas\n" +
					"5. Recuperar Atletas\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de competidores como Atletas\n" +
						"Opções" + "\n" +
						"1. Saltador\n" +
						"2. Corredor\n" +
						"3. Nadador\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: atletas.add((Atleta)leSaltador());
				break;
				case 2: atletas.add((Atleta)leCorredores());
				break;
					case 3: atletas.add((Atleta) leNadador);
					break;
				default: 
					JOptionPane.showMessageDialog(null,"Atleta para entrada não escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com atletas primeiramente");
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
					JOptionPane.showMessageDialog(null,"Entre com atletas primeiramente");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com atletas primeiramente");
					break;
				}
				salvaAatletas(atletas);
				JOptionPane.showMessageDialog(null,"Dados salvos com sucesso!");
				break;
			case 5: // Recupera Dados
				atletas = recuperaAtletas();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados recuperados com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Delegação");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Delegacao d1 = new Delegacao();
		d1.menuDelegacao();

	}

}
