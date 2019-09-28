package com.prj.original;

public class ListaDupla {

	protected ElementoLD primeiro, ultimo, atual = null;
	
		
	public int comprimento() {
	int comp = 0;
	atual = primeiro;
	while(atual != null) {
	comp++;
	atual = atual.proximo;
	}
	return comp;
	}
	
	public void inserePrimeiro(int v, boolean b) {
	ElementoLD novo = new ElementoLD(v, b);
	if (estaVazio()) {
	primeiro = novo;
	ultimo = novo;
	atual = novo;
	} else {
	novo.proximo = primeiro;
	primeiro.anterior = novo;
	primeiro = novo;
	}
	}

	public void insereUltimo(int v, boolean b) {
	ElementoLD novo = new ElementoLD(v, b);
	if (estaVazio()) {
	primeiro = novo;
	ultimo = novo;
	atual = novo;
	} else {
	ultimo.proximo = novo;
	novo.anterior = ultimo;
	ultimo = novo;
	ultimo.valor = v;
	 }
	}
	
	public void insereNaPosicao(int v, int pos, boolean b) {
	ElementoLD novo = new ElementoLD(v, b);
	atual = primeiro;
	for (int i=0; i<pos; i++) {
	atual = atual.proximo;
	}
	novo.proximo=atual.proximo;
	novo.anterior = atual;
	atual.proximo.anterior = novo;
	atual.proximo=novo;
	}

	public void removePrimeiro() {
	primeiro.proximo.anterior = null;
	primeiro = primeiro.proximo;
	
	}
	
	public void removeUltimo() {
	ultimo.anterior.proximo = null;
	ultimo = ultimo.anterior;
	}
	
	public void removeNaPosicao(int pos) {
		ElementoLD temp;
		atual = primeiro;
		for (int i=0; i<pos; i++) {
		atual = atual.proximo;
		}
		temp=atual.proximo;
		atual.proximo = temp.proximo;
		temp.proximo.anterior = atual;
		}

	
	public boolean estaVazio() {
		return primeiro == null;
		}

	public int buscaElemento(int v) {
		int cont = 0;
		atual = primeiro;
		while(atual != null && atual.valor != v) {
		atual = atual.proximo;
		cont ++;
		}
		if (atual != null)
		return cont;
		return -1;
		}

}

	

