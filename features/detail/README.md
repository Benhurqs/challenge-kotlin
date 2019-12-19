# DETAIL

É a tela responsável por exibir os detalhes do imóvel selecionado.

![MainList views](../../imgs/detail.png?raw=true) 

## Arquitetura 

Esse módulo está ligado a 2 módulos: UI-Components e Base.
A organização de pastas é feita por tipo:

- Adapter: PhotosAdapter - Responsável por preencher todos as imagens do imóvel. 
- Contract: DetailPresenterContract e DetailView - São os contratos de comunicação entre a View e o Presenter.
- Presenter: DetailPresenter - Responsável por manipular os dados do imóvel e controlar a View.
- UI/Activity: DetailActivity - Activity responsável por renderizar is dados do imóvel.


## Teste

O código esta coberto com testes unitários e teste de interface.
Abaixo as tasks para rodar todas os teste no código:


- ```gradle :features:detail:test``` (Roda todos os teste unitários)
- ```gradle :features:detail:connectedAndroidTest``` (Roda todos os testes de interface)
Para executar os testes de interface é necessário ter um device conectado

  

