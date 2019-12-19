# Zap Challenge - Módulo UI-COMPONENTS

Esse módulo é responsável por classes que ajudam na renderização das telas e podem ser utilizadas por mais de um módulo. Nele também contém todas as cores e fontes compartilhadas pelo projeto. 
Essas classes estão separadas por tipo: Adapter, Animation, Views e pelos arquivos de estilização como fonts e cores.  


## Adapter
- DefaultViewHolder: Criado para auxiliar a criação de adapters que são utilizados por recyclerviews. 
É de fácil utilização, segue o exemplo abaixo: 

```
    class SampleAdapter()  : RecyclerView.Adapter<DefaultViewHolder>() {
       ...
    }
```

## Animation
- AnimationView: Essa classe auxilia nas animações.
Segue um exemplo de utilização da animacao circular que é chamada quando apartir do botão de filtro (filter_btn)

``` 
AnimationView.startRevealCircularAnimation(filter, filter_btn.x.toInt(), filter_btn.y.toInt(), object :
                        AnimationView.AnimatorViewListener{
                        override fun onAnimationEnd(animation: Animator?) {

                        }

                        override fun onAnimationStart(animation: Animator?) {
                            
                        }
                    })
```


## Views
- ErrorViewRender: É uma framelayout que renderiza uma tela de erro, com um botão de "tentar novamente".
A utilização é feita diretamente no layout (xml).

``` 
<com.benhurqs.uicomponents.ui.views.ErrorViewRender
        android:id="@+id/error_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"/>
```


![Error view](../../imgs/error_view.png?raw=true) 

## Fonts
Dentro da pasta ``` res/font ``` existem as fontes utilizadas no projeto:
- montserrat e montserrat_bold
- roboto, roboto_light, roboto_medium e roboto_bold

Segue exemplo de utilização:

```
<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        ...
        android:fontFamily="@font/roboto_light" />

```



