#ifndef __LLARTIGOS
#define __LLARTIGOS

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

typedef struct sNomeAutor {
	char *nome;
}	NomeAutor;

typedef struct sLAutor {
	NomeAutor autor;
	struct sLAutor *next;
}	*LAutor, NLAutor;

typedef struct sLArtigo {
	int nart;
	int nautores;
	struct sLArtigo *next;
} *LArtigo, NLArtigo;

typedef struct sLFileg {
	int nautor;
	int nartigo;
	struct sLFileg *next;
} *LFileg, NLFileg;

LAutor insereAutor(LAutor, char *) ;
LArtigo criaArtigoLL(LArtigo, int) ;
LArtigo insereArtigoLL(LArtigo, int) ;
LFileg insereAutArt(LFileg, int, int) ;

/*void listarLL(LArtigo ) ;
void listarArtigo(LArtigo ) ;
void lista2(LFileg ) ; */

#endif