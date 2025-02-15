from sklearn.naive_bayes import MultinomialNB
from dados import carregar_cursos_v2, calcula_taxa_de_acerto

x, y = carregar_cursos_v2()

procentagem_treino = 0.9
tamanho_de_treino = int(procentagem_treino*len(y))
tamanho_de_teste = len(y) - tamanho_de_treino

treino_dados = x[:tamanho_de_treino]
treino_marcadores = y[:tamanho_de_treino]
teste_dados = x[-tamanho_de_teste:]
teste_macadores = y[-tamanho_de_teste:]

modelo = MultinomialNB()
modelo.fit(treino_dados, treino_marcadores)

resultado = modelo.predict(teste_dados)

taxa_de_acertos = calcula_taxa_de_acerto(resultado, teste_dados, teste_macadores)

print(taxa_de_acertos)
print(x)