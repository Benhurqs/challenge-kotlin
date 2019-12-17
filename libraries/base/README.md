# Zap Challenge - Módulo BASE

Esse módulo é responsável por classes que são compartilhadas por mais de um módulo.
Essas classes estão separadas por tipo: Actions, Model e Utils.

## Actions
A classe Actions é utilizada para comunicação entre os módulos, como os módulos das features não são dependentes entre si, elas se comunicam atravez de intents.
É necessário adicionar um intent-filter para a activity no arquivo Manifest.

```
    <application android:theme="@style/AppTheme">
        <activity android:name=".presentation.ui.activity.DetailActivity">
            <intent-filter>
                <action android:name="com.benhurqs.detail.open"/>
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
    </application>
```

Para criar uma action basta adicionar o nome do intent-filter criado, por exemplo:

```
    Intent(action).setPackage(context.packageName)
```

retornará um INTENT, onde poderá ser adicionar algum EXTRA, caso necessário.

## Models
Nessa pasta se encontram todos os objetos que são compartilhados entre os módulos.
BussinessType é um Enum que foi criado para auxiliar os dois tipos de imovel (SALE e RENTAL)


## Utils
Nessa pasta se emcontram as classes que são utilizadas para auxiliar os módulos.
- ImageUtils: É utilizada para renderezar as imagem usando a biblioteca GLIDE.
- ImovelFormatedUtils: É utilizada para fomartar os dados do obj Imovel, para ser exibido nas telas.



## Teste

O código esta coberto com testes unitários e teste de interface.
Abaixo as tasks para rodar todas os teste no código:


- ```gradle :libraries:base:test``` (Roda todos os teste unitários)
- ```gradle :libraries:base:connectedAndroidTest``` (Roda todos os testes de interface)
Para executar os testes de interface é necessário ter um device conectado




