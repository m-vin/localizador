# localizador
Localizador de arquivos escrito em Java. 
- javac TesteLocalizador.java Localizador.java LocalizadorImpl.java
- java TesteLocalizador \<tipo-de-arquivo> \<diretorio>

Exemplo: java TesteLocalizador jpg arquivos-hackaton

Obs.: Apenas os arquivos que seguirem as seguintes regras serão encontrados:

                   1. .jpg - arquivo de imagem contendo a palavra "foto" no nome;

                   2. .txt - arquivo de texto contendo uma sequencia de 8 numeros no nome;

                   3. .xls - arquivo excel contendo a palavra "base" no nome;               

