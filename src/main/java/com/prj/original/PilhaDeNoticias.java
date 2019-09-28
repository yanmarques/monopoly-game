package com.prj.original;
import java.util.Random;
public class PilhaDeNoticias {

	PilhaDinamica p = new PilhaDinamica();
	Random r = new Random();
	int i, j, valor = 0;
	
	public PilhaDeNoticias() {
		do {
			valor = r.nextInt(3)+1;
			p.empilha(valor);;
			i++;			
		}while(i < 60);
	}
	
	public void tiracarta() {
		j = p.desempilha();
		switch (j) {
		case 1: 
			//valor a pagar
			break;
		case 2:
			//valor a cobrar
			break;
		case 3:
			//pris�o
			break;
		}
	}
	
}
