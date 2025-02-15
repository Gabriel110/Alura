import csv
import pandas as pd

def carregar_cursos():
    X = []
    Y = []
    arquivo = open('cursos.csv', 'r')
    leitor  = csv.reader(arquivo)
    next(leitor)
    for home, busca, logado, comprou in leitor:
        dado = [int(home), busca, int(logado)]
        X.append(dado)
        Y.append(int(comprou))
    return X, Y

def carregar_cursos_v2():
    df = pd.read_csv('cursos.csv')
    X_df = df[['home','busca','logado']]
    Y_df = df['comprou']
    Xdumies_df = pd.get_dummies(X_df)
    Ydumies_df = Y_df
    
    return Xdumies_df.values, Ydumies_df.values

    
def calcula_taxa_de_acerto(resultado, teste_dados, marcadores_teste):
    diferencaas = resultado - marcadores_teste
    acertos = [d for d in diferencaas if d == 0]
    total_de_aceros = len(acertos)
    total_delemento = len(teste_dados)
    taxa_de_acertos = 100.0 * total_de_aceros / total_delemento
    return taxa_de_acertos