package com.prj.original;

import java.util.Random;

import javax.swing.JOptionPane;

public class Jogador {

	String nome;
	int saldo;
	ListaDupla imoveis = new ListaDupla();
			
	public Jogador() {
		this.nome = JOptionPane.showInputDialog("Nome do Jogador:");
		this.saldo = 2500;
		this.imoveis = null;
	}
	
	public int jogarDados() {
		Random r = new Random();
		Random s = new Random();
		
		int i = r.nextInt(6)+1;
		int j = s.nextInt(6)+1;
				
		return i + j;				
	}
	
}
