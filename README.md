# localizador
Localizador de arquivos escrito em Java. 
- mvn compile
- mvn package
- java -cp target/localizador-0.1.0.jar localizador.Teste xls /path/to/arquivos_hackaton

Obs.: Apenas os arquivos que seguirem as seguintes regras serão encontrados:

                   1. .jpg - arquivo de imagem contendo a palavra "foto" no nome;

                   2. .txt - arquivo de texto contendo uma sequência de 8 numeros no nome;

                   3. .xls - arquivo excel contendo a palavra "base" no nome;               

