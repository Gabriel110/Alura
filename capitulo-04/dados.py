import pandas as pd
import os

def carregar_cursos():
    df = pd.read_csv(os.path.join(os.path.dirname(__file__), 'cursos.csv'))
    X_df = df[['home','busca','logado']]
    Y_df = df['comprou']
    Xdumies_df = pd.get_dummies(X_df)
    Ydumies_df = Y_df
    
    return Xdumies_df.values, Ydumies_df.values

def tamanho_dados(x,y):
    procentagem_treino = 0.9
    tamanho_de_treino = int(procentagem_treino*len(y))
    tamanho_de_teste = len(y) - tamanho_de_treino
    treino_dados = x[:tamanho_de_treino]
    treino_marcadores = y[:tamanho_de_treino]
    teste_dados = x[-tamanho_de_teste:]
    teste_macadores = y[-tamanho_de_teste:]
    return treino_dados, treino_marcadores, teste_dados, teste_macadores
   
def calcula_taxa_de_acerto(resultado, teste_dados, marcadores_teste):
    diferencaas = resultado - marcadores_teste
    acertos = [d for d in diferencaas if d == 0]
    total_de_aceros = len(acertos)
    total_delemento = len(teste_dados)
    taxa_de_acertos = 100.0 * total_de_aceros / total_delemento
    return taxa_de_acertos