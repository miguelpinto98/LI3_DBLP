#ifndef __READ_JOURNAL
#define __READ_JOURNAL

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "writeFile.h"

#define MAXART (5000)

int verificaInt(char *) ;
int verificaAuthors(char *,int) ;
int verificaTitle(char *,int) ;
void writeRejeitados(FILE *, char *, int ) ;


#endif