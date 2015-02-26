#include "trataValidas.h"

FILE *inicWriteG_1(FILE *fg) {
	fg=fopen("G.csv","w");

	fputs("\"ano\",\"#autores\",\"#artigos\"\n",fg);
	printf("ANO\tAUTORES\tARTIGOS\n");

	return fg;
}

FILE *writeG_1(NodoAVL a, FILE *fg) {
	NodoAVL av=a;
	LArtigo l = NULL; 
	
	if(av) {
		writeG_1(av->esq,fg);
		l = av->la;
		while(l) {
			fprintf(fg,"\"%d\",\"%d\",\"%d\"\n",av->data, l->nautores, l->nart);
			printf("%d\t%d\t%d\n",av->data, l->nautores, l->nart);
			l=l->next;
		}
		writeG_1(av->dir,fg);
	}
	return fg;
}

FILE *inicWriteG_2(FILE *fg) {
	fputs("\"#autores\",\"#artigos\"\n",fg);
	printf("AUTORES\tARTIGOS\n");

	return fg;
}

FILE *inicWriteG_3(FILE *fg) {
	fputs("\"intervalo\",\"#artigos\"\n",fg);
	printf("INTERVALO\tARTIGOS\n");

	return fg;
}

FILE *inicWriteG_4(FILE *fg) {
	fputs("\"ano\",\"#autores\",\"percentagem\"\n",fg);
	printf("ANO\tAUTORES\tPERCENTAGEM\n");
	
	return fg;
}

void trataData3(char *dt, int *d1, int *d2) {
	int i=0, j=0;
	char data[5];
	memset(data,0,5);

	while(dt[i]!='-') {
		data[j]=dt[i];
		i++;
		j++;
	}
	*d1=atoi(data);
	memset(data,0,5);
	i++;
	j=0;

	while(dt[i]!='\0') {
		data[j]=dt[i];
		i++;
		j++;
	}
	*d2=atoi(data);
}

FILE *writeFile3(NodoAVL av, FILE *fg, int d1, int d2, int *res) {

	if(av) {
		if(av->data >= d1 && av->data <= d2) {
			*res+=av->nartigos;
		}
		writeFile3(av->esq,fg,d1,d2,res);
		writeFile3(av->dir,fg,d1,d2,res);
	}

	return fg;
}

void readTrataDatas3(NodoAVL a, FILE *fg) {
	FILE *fr;
	fr = fopen("datas3.txt","r");
	char datas[10];
	int data1=0, data2=0, res;
	NodoAVL av=a;

	if(!fr) {
		printf("Ficheiro datas3.txt não existe!\n");
		exit(EXIT_FAILURE);
	}
	else {
		while(fscanf(fr,"%s",datas)!=EOF) {
			res=0;
			trataData3(datas,&data1,&data2);

			fg = writeFile3(av,fg,data1,data2,&res);
			fprintf(fg,"\"%d-%d\",\"%d\"\n",data1,data2,res);
			printf("%d-%d\t%d\n",data1,data2,res);
		}
	}
}

void writeG_4(NodoAVL a, FILE *fg, int d) {
	int ntotal=0, nart=0;
	NodoAVL av=a;
	LArtigo l = NULL;

	if(av) {
		if(av->data==d) {
			ntotal=av->nartigos;
			l=av->la;
			while(l) {
				nart=l->nart;
				fprintf(fg,"\"%d\",\"%d\",\"%.2f\"\n",d,l->nautores,((float)nart/(float)ntotal)*100);
				printf("%d\t%d\t%.2f\n",d,l->nautores,((float)nart/(float)ntotal)*100);
				l=l->next;
			}
		}
		writeG_4(av->esq,fg,d);
		writeG_4(av->dir,fg,d);
	}
}

void readData4(NodoAVL av, FILE *fg) {
	FILE *fr;
	fr = fopen("datas4.txt","r");
	char data[4];
	int dt;

	if(!fr) {
		printf("Ficheiro datas4.txt não existe!\n");
		exit(EXIT_FAILURE);
	}
	else {
		while(fscanf(fr,"%s",data)!=EOF) {
			dt=atoi(data);
			writeG_4(av,fg,dt);
		}
	}
}

LFileg trataListaAutArt_2(LFileg lg, NodoAVL a, FILE *fg) {
	NodoAVL av=a;
	LArtigo l=NULL;
	LFileg l2 = lg;

	if(av) {
		l=av->la;
		while(l) {
			l2=insereAutArt(l2, l->nautores, l->nart);
			l=l->next;
		}
		l2=trataListaAutArt_2(l2, av->esq,fg);
		l2=trataListaAutArt_2(l2, av->dir,fg);		
	}
	return l2;
}

void writeG_2(LFileg lg, FILE *fg) {
	LFileg l = lg;
	while(l) {
			fprintf(fg,"\"%d\",\"%d\"\n",l->nautor,l->nartigo);
			printf("%d\t%d\n",l->nautor,l->nartigo);
			l=l->next;
		}
}

NodoAVL trataEstCompleta(NodoAVL a) {
	NodoAVL av = a;
	FILE *fg = NULL;
	LFileg l2 = NULL;

	fg = inicWriteG_1(fg);
	fg = writeG_1(av,fg);

	fg = inicWriteG_2(fg);
	l2 = trataListaAutArt_2(l2,av,fg);
	writeG_2(l2,fg);

	fg = inicWriteG_3(fg);
	readTrataDatas3(av,fg);
	
	fg = inicWriteG_4(fg);
	readData4(av,fg);

	fclose(fg);
	return av;
}