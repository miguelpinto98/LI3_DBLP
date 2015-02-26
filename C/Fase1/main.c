#include "main.h"

int minpg;

void readTCF(FILE *fr,char *file, int cf) {
	if(cf==CONF)
		readConference(file,fr);
	else
		readJournal(file,fr);
}

int readMinPG(char *file) {
	return (atoi(file));
}

int testaConfJour(char *file) {
	return (file[0]=='c') ? CONF : JOUR ;
}

void readLista(FILE *fr) {
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
				readTCF(fr,file,tcf);
			}
		}
	}
	fclose(fp);
}

int main(){
	FILE *fr=NULL;
	fr=inic_writeRejeitados(fr) ;

	readLista(fr);
	
	close_writeRejeitados(fr);
	writeEstBasica();

	return 1;
}