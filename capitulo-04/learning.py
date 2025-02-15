from sklearn.naive_bayes import MultinomialNB
from dados import carregar_cursos, calcula_taxa_de_acerto, tamanho_dados

modelo = MultinomialNB()

x, y = carregar_cursos()
treino_dados, treino_marcadores, teste_dados, teste_macadores = tamanho_dados(x, y)

modelo.fit(treino_dados, treino_marcadores)
resultado = modelo.predict(teste_dados)

taxa_de_acertos = calcula_taxa_de_acerto(resultado, teste_dados, teste_macadores)

print(taxa_de_acertos)