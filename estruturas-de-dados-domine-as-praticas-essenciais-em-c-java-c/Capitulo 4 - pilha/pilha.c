#include <stdlib.h>
#include <stdio.h>


typedef struct elemento{
    char valor;
    struct elemento * proximo;
} Elemento;

typedef struct pilha{
    int tamanho;
    Elemento * topo;
} Pilha;


Pilha * start(){
    Pilha * pilha = malloc(sizeof(Pilha))
    pilha->tamanho = 0;
    pilha->topo = NULL;
    return pilha;
}

int main(){
    printf("Teste");
}