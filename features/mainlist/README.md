# MAIN LIST

É a tela responsável por exibir a listagem de imóveis, de acordo com a escolha do filtro entre Viva Real e Zap.
Existem 3 views principais:

- Loading: É exibida quando é feita a requisição para a API de listagem de imóveis;
- List: É responsável por exibir os imóveis, assim como suas informações;
- Filter: É utilizado para filtrar entre as diferentes portais Viva Real e Zap; (Futuramente, caso o numero de filtros aumente, poderá se tornar um modulo) 

![MainList views](../../imgs/mainlist.png?raw=true) 

## Arquitetura 

Esse módulo está ligado a 3 módulos: NetWork, UI-Components e Base.
A organização de pastas é feita por tipo:

- Adapter: ListAdapter - Responsável por preencher todos os dados do imóvel, em cada item da lista. 
- Contract: MainListPresenter e MainListView - São os contratos de comunicação entre a View e o Presenter.
- Presenter: ListPresenter - Responsável por controlar se comunicar com o módulo de NetWork e controlar a View.
- UI/Activity: ListActivity - Activity responsável por renderizar a listagem de imóveis. 


### Fluxo

![MainList fluxo](../../imgs/mainListFluxo.png?raw=true) 

![MainList fluxo 2](../../imgs/mainListFluxo2.png?raw=true) 
 