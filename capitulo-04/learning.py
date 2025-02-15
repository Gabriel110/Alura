from sklearn.naive_bayes import MultinomialNB
from dados import carregar_cursos, calcula_taxa_de_acerto, tamanho_dados
from collections import Counter

def main():
    modelo = MultinomialNB()
    x, y = carregar_cursos()
    treino_dados, treino_marcadores, teste_dados, teste_macadores = tamanho_dados(x, y)

    modelo.fit(treino_dados, treino_marcadores)
    resultado = modelo.predict(teste_dados)

    taxa_de_acertos, total_delemento = calcula_taxa_de_acerto(resultado, teste_dados, teste_macadores)

    acerto_base = max(Counter(teste_macadores).values())
    taxa_de_acerto_base = 100.0 * acerto_base / len(teste_macadores)
    
    print(taxa_de_acertos)
    print(total_delemento)
    print("Taxa de acerto base: %f" % taxa_de_acerto_base)

if __name__ == "__main__":
    main()