#include "writeFile.h"

int entradasTotais=0, entradaTotalJournal=0, entradaTotalConf=0;


FILE* inic_writeRejeitados(FILE *fr) {
	fr=fopen("E.txt","w");

	fputs("Lista Rejeitadas\n",fr);
	fputs("----------------\n",fr);

	return fr;
}

void writeRejeitados(FILE *fr, char *file, int rej) {
	fprintf(fr,"%s %d\n",file,rej);
}

void close_writeRejeitados(FILE *fr) {
	fclose(fr);
}

void writeEstBasica() {
	FILE *fb;
	fb=fopen("D.txt","w");

	int artigos=entradaTotalJournal+entradaTotalConf;
	int rejeitadas=entradasTotais-artigos;

	fputs("Estatistica basica\n", fb);
	fputs("------------------\n", fb);

	fprintf(fb, "%d entradas\n", entradasTotais);
	fprintf(fb, "%d rejeitadas\n", rejeitadas);
	fprintf(fb, "%d artigos\n", artigos);
	fprintf(fb, "  %d em revista\n", entradaTotalJournal);
	fprintf(fb, "  %d em conferencia\n", entradaTotalConf);

	fclose(fb);
}