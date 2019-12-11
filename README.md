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
- [MainList](./features/detail/README.md)  
- [Detail](./features/mainlist/README.md)


![Alt text](./imgs/modules.png?raw=true) 


## Arquitetura

O aplicativo foi feito seguindo o MVP (Model - View - Presenter) e UseCases para as lógicas de negócio do Viva Real e Zap.


### Camadas


- [DATA LAYER](./DATALAYER.md)
- [PRESENTATION LAYER](./PRESENTATIONLAYER.md)
- [DOMAIN LAYER](./DOMAINLAYER.md)


