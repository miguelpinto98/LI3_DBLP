#ifndef __MAIN
#define __MAIN

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "readJournal.h"
#include "readConference.h"
#include "writeFile.h"

#define CONF (-9999)
#define JOUR (-8888)
#define MAXART (5000)

void readTCF(FILE *,char *, int ) ;
void readConference(char *, FILE*) ;
void readJournal(char *, FILE *) ;
FILE* inic_writeRejeitados(FILE *) ;
void close_writeRejeitados(FILE *) ;

#endif