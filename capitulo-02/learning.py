from sklearn.naive_bayes import MultinomialNB
from dados import carregar_dados, calcula_taxa_de_acerto

x, y = carregar_dados()

treino_dados = x[:90]
treino_marcadores = y[:90]
teste_dados = x[-9:]
teste_macadores = y[-9:]

modelo = MultinomialNB()
modelo.fit(treino_dados, treino_marcadores)

resultado = modelo.predict(teste_dados)

taxa_de_acertos = calcula_taxa_de_acerto(resultado, teste_dados, teste_macadores)

print(taxa_de_acertos)