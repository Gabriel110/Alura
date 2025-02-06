from sklearn.naive_bayes import MultinomialNB

#[É gordinho, Tem perninha curta, faz auau]
porco1 = [1, 1, 0]
porco2 = [1, 1, 0]
porco3 = [1, 1, 0]
cachorro4 = [1, 1, 1]
cachorro5 = [0, 1, 1]
cachorro6 = [0, 1, 1]

dados = [porco1, porco2, porco3, cachorro4, cachorro5, cachorro6]
macadores = [1, 1, 1, -1, -1, -1]

misterioso1 = [1, 1, 1]
misterioso2 = [1, 0, 0]
misterioso3 = [0, 0, 1]

misteriosos = [misterioso1, misterioso2, misterioso3]
marcado_resultado = [-1, 1, -1]

modelo = MultinomialNB()

modelo.fit(dados, macadores)
resultado = modelo.predict(misteriosos)

diferencas = resultado - marcado_resultado
acertos = [int(d) for d in diferencas if d == 0]
totalAcerto = len(acertos)
tamanhoMisteriosos = len(misteriosos)
porcentagem = totalAcerto *100 / tamanhoMisteriosos
literal = [("porco" if i == 1 else "cahorro") for i in resultado]

print(literal)
print(f"Acertos: {porcentagem:.0f}%")


