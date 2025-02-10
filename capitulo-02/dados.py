import csv

def carregar_dados():
    X = []
    Y = []
    arquivo = open('acesso.csv', 'r')
    leitor  = csv.reader(arquivo)
    next(leitor)
    for home, como_funciona, contato, comprou in leitor:
        dado = [int(home), int(como_funciona), int(contato)]
        X.append(dado)
        Y.append(int(comprou))
    return X, Y

def calcula_taxa_de_acerto(resultado, teste_dados, marcadores_teste):
    diferencaas = resultado - marcadores_teste
    acertos = [d for d in diferencaas if d == 0]
    total_de_aceros = len(acertos)
    total_delemento = len(teste_dados)
    taxa_de_acertos = 100.0 * total_de_aceros / total_delemento
    return taxa_de_acertos