#ifndef __WRITEFILE
#define __WRITEFILE

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>


FILE *inic_writeRejeitados() ;
void writeRejeitados(FILE *, char *, int ) ;
void close_writeRejeitados(FILE *) ;


void writeEstBasica() ;


#endif