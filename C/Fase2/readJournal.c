#include "readJournal.h"

int minpg, entradaTotalJournal, entradasTotais;

int verificaInt(char *art){
	int i=0;
	
	while((art[i]!=' ') && art[i] !='\n') {
		i++;
	}

	if(art[i]==' ')
		return i+1;
	else
		return 0;
}

int verificaAuthors(char *art, int iaut, int *nautor, LAutor *laut) {
	int i=iaut, okAuth=1, naut=0, j=0;
	char autor[1000];
	LAutor lr = NULL;

	if(art[i]==':')
		okAuth=0;

	memset(&autor,0,1000);

	while(art[i]!=':' && art[i]!='\n' && okAuth) {
		if(art[i]==',' && art[i+1]==' ') {
			naut++;
			i++;
			lr=insereAutor(lr,autor);
			j=0;
			memset(&autor,0,1000);
		}
		else {
			autor[j]=art[i];
			j++;
		}
		i++;
	}
	
	if(art[i]==':')
		lr=insereAutor(lr,autor);

	if(art[i]=='\n')
		okAuth=0;

	*nautor=(naut+1);
	*laut=lr;
	
	return (okAuth) ? (i) : okAuth;
}

int verificaE(char* art) {
	int i=1, encontrou=1;

	if(art[i]=='D' || art[i]=='d') {
		if(art[i+1]=='I' || art[i+1]=='i')
			if(art[i+2]=='T' || art[i+2]=='t')
				if(art[i+3]=='O' || art[i+3]=='o')
					if(art[i+4]=='R' || art[i+4]=='r')
					 	if(art[i+5]=='I' || art[i+5]=='i')
							if(art[i+6]=='A' || art[i+6]=='a')
								if(art[i+7]=='L' || art[i+7]=='l')
									encontrou=0;
									}
	else {
		if(art[i]=='R' || art[i]=='r')
			if(art[i+1]=='R' || art[i+1]=='r')
				if(art[i+2]=='A' || art[i+2]=='a')
					if(art[i+3]=='T' || art[i+3]=='t')
						if(art[i+4]=='A' || art[i+4]=='a')
							encontrou=0;
	}
	return encontrou;
}

int verificaP(char* art) {
	int i=1, encontrou=1;

	if(art[i]=='R' || art[i]=='r') {
		if(art[i+1]=='E' || art[i+1]=='e')
			if(art[i+2]=='F' || art[i+2]=='f')
				if(art[i+3]=='A' || art[i+3]=='a')
					if(art[i+4]=='C' || art[i+4]=='c')
					 	if(art[i+5]=='E' || art[i+5]=='e')
									encontrou=0;
									}
	return encontrou;
}

int verificaO(char* art) {
	int i=1, encontrou=1;

	if(art[i]=='B' || art[i]=='b') {
		if(art[i+1]=='I' || art[i+1]=='i')
			if(art[i+2]=='T' || art[i+2]=='t')
				if(art[i+3]=='U' || art[i+3]=='u')
					if(art[i+4]=='A' || art[i+4]=='a')
					 	if(art[i+5]=='R' || art[i+5]=='r')
							if(art[i+6]=='Y' || art[i+6]=='y')
									encontrou=0;
									}
	return encontrou;
}

int verificaI(char* art) {
	int i=1, encontrou=1;
	
	if(art[i]=='N' || art[i]=='n') {
		if(art[i+1]==' ')
			if(art[i+2]=='M' || art[i+2]=='m')
				if(art[i+3]=='E' || art[i+3]=='e')
					if(art[i+4]=='M' || art[i+4]=='m')
					 	if(art[i+5]=='O' || art[i+5]=='o')
							if(art[i+6]=='R' || art[i+6]=='r')
								if(art[i+7]=='Y' || art[i+7]=='y')
									if(art[i+8]==' ')
										if(art[i+9]=='O' || art[i+9]=='o')
											if(art[i+10]=='F' || art[i+10]=='f')
												encontrou=0;
												}
	else {
		if(art[i]=='S' || art[i]=='s')
			if(art[i+1]=='B' || art[i+1]=='b')
				if(art[i+2]=='N' || art[i+2]=='n')
							encontrou=0;
	}
	return encontrou;
}

int verificaTitle(char* art, int itt) {
	int i=itt+1, okTit=1;

	if(art[i]=='.')
		okTit=0;
	
	while((art[i]!='.') && (art[i]!='\n') && okTit) {
		if(((art[i]=='e') || (art[i]=='E')) && (okTit))
			okTit = verificaE(art+i);
		if(((art[i]=='p') || (art[i]=='P')) && (okTit))
			okTit=verificaP(art+i);
		if(((art[i]=='o') || (art[i]=='O')) && (okTit))
			okTit=verificaO(art+i);
		if(((art[i]=='i') || (art[i]=='I')) && (okTit))
			okTit=verificaI(art+i);
			
		i++;
	}
	
	if(art[i]=='\n')
		okTit=0;
	
	return (okTit) ? i : okTit ;
}

static int verificaNomeRevista(char *art, int inr) {
	int i=inr, okNome=0;

	if(art[i]=='.') {
		i+=2;
		okNome=1;
	}

	while(art[i]!='(' && art[i]!='\n' && okNome) {
		i++;
	}

	if(art[i]=='(' && okNome)
		return i;
	else
		return 0;
}

static int verificaSigla(char *art, int isgl) {
	int i=isgl+1, okSigla=1;

	if(art[i+1]==')' && okSigla)
		okSigla=0;

	while(art[i]!=')' && art[i]!='\n' && okSigla) {
		i++;
	}

	if(art[i]==')' && okSigla)
		return (i+1);
	else
		return 0;
}

static int verificaVolume(char *art, int ivol) {
	int i=ivol, okVolume=0;

	if(art[i]==' ') {
		okVolume=1;
		i+=1;
	}

	while(art[i]!='(' && art[i]!='\n' && okVolume) {
		okVolume=isdigit(art[i]);
		i++;
	}

	if(art[i]=='(' && okVolume)
		return (i+1);
	else
		return 0;
}

static int verificaNumero(char *art, int inum) {
	int i=inum, okNum=1;

	if(art[i]==')')
		okNum=0;

	while(art[i]!=')' && art[i]!='\n' && okNum) {
		okNum=isdigit(art[i]);
		i++;
	}

	if(art[i]==')' && art[i+1]==':' && okNum)
		return (i+2);
	else
		return 0;
}

static int verificaPaginas(char *art, int ipg) {
	int i=ipg, j=0, okPag=1, pagInf=0, pagSup=0, paginas=minpg;
	char num1[10];
	char num2[10];

	/*Limpar Buffer*/
	memset(&num1,0,10);
	memset(&num2,0,10);

	while(art[i]!='-' && art[i]!='\n' && okPag) {
		okPag=isdigit(art[i]);
		num1[j]=art[i];
		i++;
		j++;
	}

	if(art[i]=='-' && okPag) {
		okPag=1;
		i+=1;
		j=0;
	}
	else
		okPag=0;

	while(art[i]!=' ' && art[i]!='\n' && okPag) {
		okPag=isdigit(art[i]);
		num2[j]=art[i];
		i++;
		j++;
	}

	if(art[i]==' ' && okPag) {
		pagInf=atoi(num1);
		pagSup=atoi(num2);
		i+=1;
	}
	else
		okPag=0;

	if(((pagSup-pagInf+1) >= paginas) && okPag)
		return i;
	else
		return 0;
}

static int verificaAno(char *art, int iano, int *data) {
	int i=iano, okAno=0, j=0;
	char anoArt[5];

	if(art[i]=='(') {
		okAno=1;
		i+=1;
		memset(&anoArt,0,5);
	}

	if(art[i+1]==')' && okAno)
		okAno=0;

	while(art[i]!=')' && art[i]!='\0' && okAno) {
		okAno=isdigit(art[i]);
		anoArt[j]=art[i];
		i++;
		j++;
	}

	if(art[i]==')' && okAno) {
		*data=atoi(anoArt);
		return 1;
	}
	else
		return 0;
}

/**TESTE FASE2B**/
/*HashTable table;

void faztudo(HashTable th, LAutor la, int na) {
	LAutor cabeca;
	int nelem = na, count=0, posatual=1, i,x;
	NomeAutor prox = la->autor;

	LAutor laut = NULL;

	for(i=0; i<nelem; i++) {
		cabeca=la;
		count=1;
		
		x=InsertTable(th,prox.nome,(Info*)&laut);

		if(RetrieveTable(th, prox.nome, (Info*)&laut)==(-1)) {
			while(la) {
				printf("%s\n", la->autor.nome);
				if(count==posatual) ;
				else
				count++;
				la=la->next;
			}
		}
		else
			printf("%s\n", la->autor.nome);
	}
}*/

static int treatsJournal(char *art, NodoAVL *avlart, FILE *fpub) {
	int testJournal=0, data=0, nautores=0, ctr=0;
	LAutor laut;
	
	testJournal=verificaInt(art);
	if(testJournal)
		testJournal=verificaAuthors(art,testJournal,&nautores,&laut);
		if(testJournal)
			testJournal=verificaTitle(art,testJournal);
			if(testJournal)
				testJournal=verificaNomeRevista(art,testJournal);
				if(testJournal)
					testJournal=verificaSigla(art,testJournal);
					if(testJournal)
						testJournal=verificaVolume(art,testJournal);
						if(testJournal)
							testJournal=verificaNumero(art,testJournal);
							if(testJournal)
								testJournal=verificaPaginas(art,testJournal);
								if(testJournal) 
									testJournal=verificaAno(art,testJournal,&data);
									if(testJournal) {
										(*avlart)=inserirArtigoAVL(*avlart,data,nautores,&ctr);
										/*faztudo(table,laut,nautores);*/

										while(laut) {
											fprintf(fpub, "%s, ", laut->autor.nome);
											laut=laut->next;
										}
										fprintf(fpub, "%d\n",data);
										return testJournal;
									}
	return 0;
}

NodoAVL readJournal(char *file, FILE *fr, NodoAVL avlart, FILE *fpub) {
	FILE *fp;
	fp = fopen(file,"r");
	char art[MAXART];
	int entry=0,aceite=0, rejeita=0;
	int okJour;

	if(!fp)
		printf("Ficheiro %s não existe!\n",file);
	else {
			aceite=0;
			rejeita=0;
		while(fgets(art,sizeof(art),fp)) {
			okJour = treatsJournal(art,&avlart,fpub);
			entry++;
			entradasTotais++;
			
			if(okJour) {
				aceite++;
				entradaTotalJournal++;
			}
			else
				rejeita++;
		}
	fprintf(fr,"%s %d\n",file,rejeita);
	}
	fclose(fp);
	return avlart;
}