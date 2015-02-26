#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "readLista.h"
#include "readJournal.h"
#include "readConference.h"
#include "LLArtigos.h"
#include "AVLArtigos.h"
#include "trataValidas.h"

int entradasTotais=0;

int main(){
	FILE *fr=NULL;
	NodoAVL av = NULL;
	FILE *fpub = NULL;
	fr=inic_writeRejeitados(fr) ;

	fpub = fopen("public.txt","w");

	readLista(&av,fr,fpub);
	
	close_writeRejeitados(fr);
	writeEstBasica();
	
	av=trataEstCompleta(av);

	fclose(fpub);

	/*lista(av);*/
	return 1;
}