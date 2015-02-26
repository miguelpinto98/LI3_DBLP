#include "HashTable.h"
#include "hashfunction.h"

int Hash(KeyType key) {
	ub4 h = hash( (unsigned char *)key, 9, 0);
	return h%HASHSIZE;	
}

void InitializeTable(HashTable t) {
	int i = 0;
	for (i=0;i<HASHSIZE;i++) 
		t[i]=NULL;
}

void ClearTable(HashTable t) {
	int i = 0;
	Entry *aux;
	for (i=0;i<HASHSIZE;i++) {
		while(t[i]) {
			aux=t[i];
			t[i]=t[i]->next;
			free(aux);
		}
	}
}     

int InsertTable(HashTable t, KeyType key, Info i) {
    Entry *new, *aux;
    int h=Hash(key);
	aux=t[h];
	while ((aux)&&(strncmp(aux->key,key,9))) {
		aux=aux->next;
	}
	if (! aux) {
		new=(Entry*)malloc(sizeof(Entry));
		strncpy(new->key,key,9);
		new->info=i;
		new->next=t[h];
		t[h]=new; 
	}
	else { // Lida com repetidos (escreve por cima)
		strncpy(aux->key,key,9);
		aux->info=i;	
	}
	return 0;
}

int DeleteTable(HashTable t, KeyType key, Info* i) {
    int h = Hash(key);
	Entry *aux1,*aux2;
	if (!t[h]) return (-1);
	aux1=NULL; aux2=t[h];
	while ((aux2)&&(strncmp(aux2->key,key,9))) {
		aux1=aux2;
		aux2=aux2->next;
	}
	if (! aux2) return (-1);
	*i=aux2->info;
	if (! aux1) {
		t[h]=t[h]->next;
		free(aux2);
	}
	else {
		aux1->next=aux2->next;
		free(aux2);
	}
	return 0;
}

int RetrieveTable(HashTable t, KeyType key, Info* i) {
    int h = Hash(key);
	Entry *aux;
	if (!t[h]) return (-1);
	aux=t[h];
	while ((aux)&&(strncmp(aux->key,key,9))) {
		aux=aux->next;
	}
	if (! aux) return (-1);
	*i=aux->info;
	return 0;
}
