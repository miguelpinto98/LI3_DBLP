#ifndef __READ_CONFERENCE
#define __READ_CONFERENCE

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include "readJournal.h"
#include "LLArtigos.h"
#include "AVLArtigos.h"

#define MAXART (5000)

extern int entradasTotais;

void writeRejeitados(FILE *, char *, int ) ;

#endif