package com.prj.original;
public class PilhaDinamica {
	
	private ElementoP topo = null;
		
	public boolean estaVazia() {
		return topo == null;
	}
	
	public void empilha(int v) {
		ElementoP novo = new ElementoP(v);
		if (topo == null)
			topo = novo;
		else {
			novo.proximo = topo;
			topo = novo;
		}
	}
	
	public int desempilha() {
		int retVal = topo.valor;
		topo = topo.proximo;
		return retVal;
	}
}
