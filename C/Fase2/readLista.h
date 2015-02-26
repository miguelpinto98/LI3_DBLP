#ifndef __LISTA
#define __LISTA

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "LLArtigos.h"
#include "AVLArtigos.h"

#define CONF (-9999)
#define JOUR (-8888)
#define MAXART (5000)

extern int entradasTotais;

NodoAVL readTCF(NodoAVL, FILE *, char *, int ,FILE*) ;
NodoAVL readConference(char *, FILE*, NodoAVL, FILE *) ;
NodoAVL readJournal(char *, FILE *, NodoAVL, FILE *) ;
void readLista(NodoAVL *, FILE *, FILE *) ;
void writeEstBasica() ;
FILE* inic_writeRejeitados(FILE *) ;
void close_writeRejeitados(FILE *) ;

#endif