#ifndef _TABELA_HASH_H
#define _TABELA_HASH_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "LLArtigos.h"

#define HASHSIZE    400001    // numero primo

typedef char KeyType[9];
typedef void *Info;

typedef struct entry {
  KeyType  key;
  Info info;
  struct entry *next;
} Entry;

typedef Entry *HashTable[HASHSIZE];

// funcao de hash  
int Hash(KeyType);

// inicializa a tabela de hash
void InitializeTable(HashTable);

// limpa a tabela de hash
void ClearTable(HashTable);

// insere uma nova associacao entre uma chave nova e a restante informacao
int InsertTable(HashTable, KeyType, Info);

// apaga o elemento de chave k da tabela
int DeleteTable(HashTable, KeyType, Info*);

// procura na tabela o elemento de chave k, e retorna-o
int RetrieveTable(HashTable, KeyType, Info*);

#endif
