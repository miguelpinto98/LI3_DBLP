#include "readLista.h"

int minpg, entradaTotalJournal=0, entradaTotalConf=0;

NodoAVL readTCF(NodoAVL av,FILE *fr,char *file, int cf, FILE *fpub) {
	if(cf==CONF)
		av = readConference(file,fr,av,fpub);
	else
		av = readJournal(file,fr,av,fpub);
	return av;
}

int readMinPG(char *file) {
	return (atoi(file));
}

int testaConfJour(char *file) {
	return (file[0]=='c') ? CONF : JOUR ;
}

void readLista(NodoAVL *av, FILE *fr, FILE *fpub) {
	FILE *fp;
	fp = fopen("lista.txt","r");
	char *file;
	file=(char*)malloc(25*sizeof(char));
	int flag, tcf;
	
	if(!fp) {
		printf("Ficheiro não existe!\n");
		exit(EXIT_FAILURE);
	}
	else {
		flag=0;
		while(fscanf(fp,"%s",file)!=EOF) {
			if(!flag) {
				minpg=readMinPG(file);
				flag=1;
			}
			else {
				tcf = testaConfJour(file);
				(*av)=readTCF(*av,fr,file,tcf,fpub);
			}
		}
	}
	fclose(fp);
}

FILE* inic_writeRejeitados(FILE *fr) {
	fr=fopen("E.txt","w");

	fputs("Lista Rejeitadas\n",fr);
	fputs("----------------\n",fr);

	return fr;
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

void close_writeRejeitados(FILE *fr) {
	fclose(fr);
}
