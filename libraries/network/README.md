# Zap Challenge - Módulo NETWORK

Esse módulo é responsável por chamada para as APIs.
Essas classes estão separadas por pastas: Data, Domains.

## Data
Nessa pasta existem 3 classes:
- APICallback: Utilizada para auxiliar na comunicação entre as classes.
- ZapAPI: Onde se encontra todos os endpoints utilizados no projeto.
- ZapAPIService: Está sendo utilizado Retrofit para fazer as chamadas as APIs. Essa classe é responsável por toda a configuração do retrofit.
A url base se encontra dentro do ```build.gradle``` e pode ser utlizada com o seguinte commando ```BuildConfig.SERVER_URL ```


## Domain
Nessa pasta se encontram outras duas subpastas:

- **Repository**: Responsável por gerenciar as chamadas na API e as respostas. É a unica classe que se comunica com os outros módulos.
- **UseCase**: Existem duas classes VivaRealUseCase e ZaUseCase, as duas classes herdam do ListUseCase. Elas são responsáveis pelas regras de negócio de cada tipo portal (Zap e Viva Real)

## Teste

O código esta coberto com testes unitários.
Abaixo a task para rodar todas os teste no código:


- ```gradle :libraries:network:test``` (Roda todos os teste unitários)


