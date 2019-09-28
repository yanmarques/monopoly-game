package com.prj.original;
import java.util.Random;
public class Tabuleiro {

	ListaDupla listaTabuleiro = new ListaDupla();
	Random r = new Random();
	int i, j, valor = 0;
	
	public Tabuleiro() {
		listaTabuleiro.insereUltimo(0, false);
		do {
			j = r.nextInt(10);
			System.out.println(j);
			if(j<7){
				valor = (r.nextInt(50)+1)*100;
				System.out.println(valor);
				listaTabuleiro.insereUltimo(valor, false);
				System.out.println(listaTabuleiro.ultimo.valor);
				System.out.println(listaTabuleiro.ultimo.casa);
			}else{
				listaTabuleiro.insereUltimo(0, true);
				
			}
			i++;
			System.out.println("/");
		}while (i < 38);
		listaTabuleiro.ultimo.proximo = listaTabuleiro.primeiro;
		listaTabuleiro.primeiro.anterior = listaTabuleiro.ultimo;
	}
}
