# Zap Challenge 

O Zap Challenge é um aplicativo android que foi desenvolvido para resolver o desafio do Grupo Zap. 
O aplicativo foi desenvolvido em módulos, utilizando Kotlin.


## Build Variants

- Release: Build de produção.
- Debug: Build de desenvolvimento.

## Módulos

Existem 5 módulos separados em dois tipos:

#### Libraries: 
- [Base](./libraries/base/README.md)
- [Network](./libraries/network/README.md)
- [Ui-Components](./libraries/ui-components/README.md)

#### Features: 
- [MainList](./features/mainlist/README.md)  
- [Detail](./features/detail/README.md)


![Alt text](./imgs/modules.png?raw=true) 


## Arquitetura

O aplicativo foi feito seguindo o MVP (Model - View - Presenter) e UseCases para as lógicas de negócio do Viva Real e Zap.


### Teste

O código esta coberto com testes unitários e teste de interface.
Abaixo as tasks para rodar todas os teste no código:


- gradle test (Roda todos os teste unitários)
- gradle connectedAndroidTest (Roda todos os testes de interface)
Para executar os testes de interface é necessário ter um device conectado




