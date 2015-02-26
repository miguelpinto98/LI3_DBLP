#include "AVLArtigos.h"

NodoAVL inserirArtigoAVL(NodoAVL av, int ano, int naut, int *ctr) {
	NodoAVL aux;

	if(!av) {
		aux = (NodoAVL)malloc(sizeof(NNodoAVL));
		aux->data = ano;
		aux->nartigos = 1;
		aux->la = criaArtigoLL(NULL, naut);
		aux->balance = 0;
		aux->esq = NULL;
		aux->dir = NULL;
		*ctr=1;
		av=aux;
	}
	else {
		if(av->data == ano) {
			av->nartigos++;
			av->la = insereArtigoLL(av->la,naut);
		}
		else {
			if(av->data > ano) {
				av->esq = inserirArtigoAVL(av->esq, ano, naut, ctr);
				if(*ctr) {
					switch(av->balance) {
						case (-1) :
							av = balanceEsq(av,ctr); break;
						break;
						case (0) :
							av->balance = (-1); break; 
						case (1) :
							av->balance = (0);
							*ctr=0; break;
					}
				}
			}
			else {
				av->dir = inserirArtigoAVL(av->dir, ano, naut, ctr);
				if(*ctr) {
					switch(av->balance) {
						case (-1) :
							av->balance = 0;
							*ctr=0; break;
						case (0) :
							av->balance = (1); break; 
						case (1) :
							av = balanceDir(av,ctr); break;
					}
				}
			}
		}
	}
	return av;
}

NodoAVL balanceEsq (NodoAVL raiz, int* aum) {
	NodoAVL ls = raiz->esq ; 
	NodoAVL rs ;
	
	switch (ls->balance) {
		case (-1) :
			raiz->balance = ls->balance = 0 ;
			raiz = roda_dir(raiz) ; break ;
		case (0) :
			break ;
		case (1) :
			rs = ls->dir ;
			switch (rs->balance) { 
				case (1) :
					raiz->balance = 0 ;
					ls->balance = -1 ; break ;
				case (0) :
					raiz->balance = ls->balance = 0 ; break ;
				case (-1) :
					raiz->balance = 1 ;
					ls->balance = 0 ; break ;
			}
			rs->balance = 0 ;
			raiz->esq = roda_esq(ls) ;
			raiz = roda_dir(raiz) ;
			*aum = 0 ;
	}
	return raiz ;
}

NodoAVL balanceDir (NodoAVL raiz , int* aum) {
	
	NodoAVL rs = raiz->dir ; /** sub-árvore direita da raiz **/
	NodoAVL ls ; 
	
	switch (rs->balance) {
		case 1 :
			raiz->balance = rs->balance = 0 ;
			raiz = roda_esq(raiz) ;
			*aum = 0 ; break ;
		case 0 : 
			break ; /** Já está balanceada **/
		case (-1) :
			ls = rs->esq ; /** sub-árvore esquerda de rs **/
			switch (ls->balance) {
				case 1 :
					raiz->balance = -1 ;
					rs->balance = 0 ; break ;
				case 0 :
					raiz->balance = rs->balance = 0 ; break ;
				case -1 :
					raiz->balance = 0 ;
					rs->balance = 1 ; break ;
			}
			ls->balance = 0 ;
			raiz->dir = roda_dir(rs) ;
			raiz = roda_esq(raiz) ;
			*aum = 0 ;
	}
	return raiz ;
}

NodoAVL roda_esq (NodoAVL raiz) {
	
	NodoAVL a = raiz ;
	if (raiz && raiz->dir) {
		a = raiz->dir ;
		raiz->dir = a->esq ;
		a->esq = raiz ;
	}
	return a ;
}

NodoAVL roda_dir (NodoAVL raiz) {
	
	NodoAVL a = raiz ;
	if (raiz && raiz->esq) {
		a = raiz->esq ;
		raiz->esq = a->dir ;
		a->dir = raiz ;
	}
	return a ;
}

/*void lista(NodoAVL a)
{
	if(a)
	{ 
		lista(a->esq);
		printf("%d\n", a->data);
		printf("Numero de artigos: %d\n", a->nartigos);
		listarLL(a->la);
		lista(a->dir);
	}
*/