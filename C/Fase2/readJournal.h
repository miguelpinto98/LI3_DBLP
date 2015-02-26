#ifndef __READ_JOURNAL
#define __READ_JOURNAL

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "LLArtigos.h"
#include "AVLArtigos.h"
#include "HashTable.h"

#define MAXART (5000)

extern int entradasTotais;

int verificaInt(char *) ;
int verificaAuthors(char *,int, int *, LAutor *) ;
int verificaTitle(char *,int) ;
void writeRejeitados(FILE *, char *, int ) ;

#endif