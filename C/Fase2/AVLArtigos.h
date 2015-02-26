#ifndef __LLARTICLES
#define __LLARTICLES

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "LLArtigos.h"

typedef struct sNodoAVL {
	int data;
	int nartigos;
	LArtigo la;
	int balance;
	struct sNodoAVL *esq, *dir;
} *NodoAVL, NNodoAVL;

NodoAVL inserirArtigoAVL(NodoAVL, int, int, int*) ;
NodoAVL balanceEsq (NodoAVL, int* ) ;
NodoAVL balanceDir (NodoAVL, int*) ;
NodoAVL roda_esq (NodoAVL ) ;
NodoAVL roda_dir (NodoAVL ) ;

/*void lista(NodoAVL );*/

#endif