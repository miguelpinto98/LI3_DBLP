#include "LLArtigos.h"

/*LAutor insereAutor(LAutor la, char *aut) {
	LAutor aux;
	aux = (LAutor)malloc(sizeof(NLAutor));

	aux->autor.nome=strdup(aut);
	aux->next=la;

	return aux;
} */

LAutor insereAutor(LAutor la, char *aut) {
	LAutor aux = la;

	if(aux==NULL) {
		aux = (LAutor)malloc(sizeof(NLAutor));
		aux->autor.nome=strdup(aut);
		aux->next=NULL;
	}
	else {
		while(aux != NULL) {
			aux = aux->next;
		}
		aux = (LAutor)malloc(sizeof(NLAutor));
		aux->autor.nome=strdup(aut);
		aux->next=NULL;		
	}
	return aux;
}


LArtigo criaArtigoLL(LArtigo la, int n) {
	LArtigo aux;

	aux = (LArtigo)malloc(sizeof(NLArtigo));
	aux->nart = 1;
	aux->nautores = n;
	aux->next = la;

	return aux;
}

LArtigo insereArtigoLL(LArtigo la, int n) {
	if(la) {
		if(la->nautores > n) {
			la=criaArtigoLL(la,n);
		}
		else {
			if(la->nautores < n) {
				la->next = insereArtigoLL(la->next,n);
			}
			else {
				la->nart++;
				la->nautores=n;
			}
		}
	}
	else
		la=criaArtigoLL(la,n);

	return la;
}

LFileg criaListaNumAutores(LFileg l, int naut, int nart) {
	LFileg aux;

	aux = (LFileg)malloc(sizeof(NLFileg));
	aux->nautor = naut;
	aux->nartigo = nart;
	aux->next = l;

	return aux;
}

LFileg insereAutArt(LFileg l, int naut, int nart) {	
	if(l) {
		if(l->nautor > naut) {
			l=criaListaNumAutores(l,naut,nart);
		}
		else {
			if(l->nautor < naut) {
				l->next = insereAutArt(l->next,naut,nart);
			}
			else {
				l->nartigo+=nart;
			}
		}
	}
	else
		l=criaListaNumAutores(l,naut,nart);

	return l;
}

/*
void listarLL(LArtigo la) {
	if(la) {
		printf("Numero autores: %d - Numero Artigos: %d\n", la->art.nautores,la->nart);
		listarLL(la->next);
	}
}

void lista2(LFileg l) {
	if(l) {
		printf("Numero autores: %d - Numero Artigos: %d\n", l->nautor,l->nartigo);
		lista2(l->next);
	}
}*/