# processador-nota-fiscal

Esse projeto processa trechos referentes aos itens de uma nota fiscal nos formatos CSV e XML.

São mostrados:

- o valor total da nota fiscal
- o subtotal por classe fiscal

O código do projeto está no diretório `br`.

Os JARs das bibliotecas estão no diretório `libs`.

## Pré-requisitos

Você deve ter instalada uma JDK na versão 8 ou posterior.

## Compilando o projeto

Para compilar o projeto, execute:

```sh
javac -cp ".:libs/*" -d out br/com/alura/oobj/Main.java
```

> No Windows, execute: `javac -cp .;libs/*; -d out br/com/alura/oobj/Main.java`

Esse comando terá o código compilado no diretório `out`.

## Executando o projeto

Depois de compilado, você pode executar o projeto para processar o `itens-pedido.csv` da seguinte maneira:

```sh
java -cp "out:libs/*" br.com.alura.oobj.Main itens-pedido.csv
```

> No Windows, execute: `java -cp out;libs/*; br.com.alura.oobj.Main itens-pedido.csv`

Para processar o XML, basta passar `itens-pedido.xml` como parâmetro.

Deve ser exibido como resposta algo como:

```txt
## Total do pedido: 130.5

## Subtotal por classe fiscal

	Classe fiscal: 2106.90.90
	Subtotal: 112.2

	Classe fiscal: 2202.10.00
	Subtotal: 11.8

	Classe fiscal: 2203.00.00
	Subtotal: 6.5
```
