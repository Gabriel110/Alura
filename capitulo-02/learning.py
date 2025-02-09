from sklearn.naive_bayes import MultinomialNB
from dados import carregar_dados

modelo = MultinomialNB()
x, y = carregar_dados()

modelo.fit(x, y)