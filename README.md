# MyMovies
Primeros pasos con ***Jetpack Compose*** (curso Antonio Leiva)

### Índice

- [1. ¿Qué es ***Jetpack Compose***?](#1-qué-es-jetpack-compose)
- [2. ***BOX***, ***COLUMN*** y ***ROW***](#2-box-column-y-row)
- [3. ***MODIFIER***](#3-modifier)
- [4. ***TEXT***](#4-text)
- [5. ***IMAGE*** y ***ICON***](#5-image-y-icon)
  - [5.1 ***`AsyncImage`***](#51-asyncimage)
- [6. **Listas** y ***Grids***: ***`LazyColumn`***, ***`LazyRow`***, ***`LazyVerticalGrid`***, ***`LazyHorizontalGrid`***](#6-listas-y-grids-lazycolumn-lazyrow-lazyverticalgrid-lazyhorizontalgrid)
- [7. El **estado** en *JetpackCompose*](#7-el-estado-en-jetpackcompose)
- [8. ***AppBar*** y ***Scaffold***](#8-appbar-y-scaffold)
- [9. Estructurar el código en ***Jetpack Compose***](#9-estructurar-el-código-en-jetpack-compose)
- [10. ***Navegación básica con `Navigation Compose`***](#10-navegación-básica-con-navigation-compose)
- [11. Uso de ***Cards*** de ***Material Design*** en ***Jetpack Compose***](#11-uso-de-cards-de-material-design-en-jetpack-compose)
- [Referencias extra](#referencias-extra)

----


#### 1. ¿Qué es ***Jetpack Compose***?
Es un sistema de creación de interfaces declarativas para Android.

`New Project` -> `Empty Compose Activity`


#### 2. ***BOX***, ***COLUMN*** y ***ROW***
***BOX***: El *FrameLayout* de Compose.  
***COLUMN***: El *LinearLayout* vertical de Compose.  
***ROW***: El *LinearLayout* horizontal de Compose.


#### 3. ***MODIFIER***
Permite añadir apariencia o capacidades extra a un `Composable`. Es un `companion object` de una interfaz con el mismo nombre. Tiene un API de tipo ***Builder***. Es importante el orden: se van a aplicar en el orden que se hayan definido.

Hay varios tipos de modificadores:
- **De posicionamiento y tamaño**: Indica cómo se va a posicionar una vista respecto a las vistas con las que interactúa, así como el espacio que ocuparán en pantalla. Ejs.: `fillMaxWidth`, `width`, `height`.
- **De funcionalidad**: Permiten ampliar características sobre el `Composable` en el que se aplican. Ejs.: `clickable`, `horizontalScroll`, `draggable`.
- **De apariencia**: Ejs.: `background`, `padding` (en Compose, sólo existe el `padding`, no hay `margins`), `scale`, `border`, `alpha`.
- **Listeners**: Permiten escuchar ciertos eventos relacionados con la vista. Ejs.: `onFocusChanged`, `onKeyEvent`, `onSizeChanged`.


#### 4. ***TEXT***
Es el `Composable` encargado de renderizar el texto y el equivalente al TextView. Existe una versión más básica llamada ***BasicText***, pero mayormente no se utiliza ya que no aplica las guías de *Material Design*.  
En el archivo ***Type*** detro de ***ui.theme*** se definen las tipografías de *Material* que usará el Tema en ciertos puntos de la aplicación, como también en el Estilo de los textos que se necesite.


#### 5. ***IMAGE*** y ***ICON***
***IMAGE***: Es el `Composable` que permite cargar imágenes de distintas formas, aunque lo más habitual es usar un `painter`, que no hace más que **definir lo que se debe pintar en la imagen**. La librería ***Coil*** (**Co**routine **I**mage **L**oader) es la librería standard para cargar imágenes que, al estar basada en corrutinas, encaja bien en la filosofía de ***Jetpack Compose***. Esta librería ofrece un ***`painter`*** que permite cargar imágenes de forma sencilla, utilizando la función `rememberImagePainter`. Sin embargo, en ***Coil 2.0*** esta función está deprecada y, en su lugar, se utiliza otro componente (ver [inciso 5.1](#51-asyncimage)).  
***Coil*** también ofrece la posibilidad de configurar varias cosas, entre ellas por ejemplo, permite utlizar un ***`ImageRequest.Builder`*** para definir transformaciones (`CircleCropTransformation()`, `RoundedCornersTransformation()`, `crossfade`), un ***`Modifier`*** para determinar qué espacio ocupará imagen (por ejemplo, `Modifier.fillMaxSize()`), o ***`ContentScale`*** para recortar la imagen (`ContentScale.Crop`).

***ICON***: Es el `Composable` que permite añadir íconos. Una de sus sobrecargas, permite usar un ***`ImageVector`*** (por ejemplo, `Icons.Default.PlayArrow`). Otra, permite usar un recurso a través de ***`painterResource`*** (`painterResource(id = R.drawable.ic_launcher_foreground)`).

<ul>

<li>

#### 5.1 ***`AsyncImage`***
***Coil*** ofrece este `Composable` que recibe un `model` con la URL de la imagen. Pero si no es la URL, también se le puede pasar un ***`ImageRequest.Builder`***, al cual se le pasa el contexto con ***`LocalContext.current`***. Dentro del `builder`, se le puede pasar la URL en el ***`data`*** y una serie de transformaciones similar a lo que se hacía con la función `rememberImagePainter` (ver [inciso 5](#5-image-y-icon)).

</ul>  


#### 6. **Listas** y ***Grids***: ***`LazyColumn`***, ***`LazyRow`***, ***`LazyVerticalGrid`***, ***`LazyHorizontalGrid`***
Existen unas **variantes `Lazy` de `Column` y de `Row`** que permiten añadir un número indeterminado de elementos sin que esto penalice el rendimiento:

  - ***`LazyColumn`***: Define un **listado vertical de elementos**.
  - ***`LazyRow`***: Define un **listado horizontal de elementos**.
  - ***`LazyVerticalGrid`***: Define un **listado de elementos en grilla vertical**. Requiere de un parámetro llamado **`columns`**, que define la cantidad de celdas por fila en caso de pasarle un número fijo (`GridCells.Fixed(2)`) o adaptable al tamaño mínimo definido (`GridCells.Adaptive(150.dp)`). Esto es útil para cuando el dispositivo se rota, por ejemplo.
  - ***`LazyHorizontalGrid`***: Lo mismo que la vertical, pero en horizontal. En lugar del parámetro **`columns`**, requiere uno llamado **`rows`**.


#### 7. El **estado** en *JetpackCompose*
Cada vista de la UI, va a depender de un **estado**. Cada vez que ese estado cambia, se vuelve a recomponer el `Composable` (es decir, se vuelve a ejecutar la función) y la UI se actualiza de forma automática. Para esto, se utiliza la función ***`mutableStateOf()`***, a la que se le pasa el valor que se le quiere setear.  
Para evitar que se "reinicie" el estado cada vez que el `Composable` vuelve a ejecutarse, se utiliza una función llamada ***`remember{}`***, la cual recibe un *lambda* que se ejecuta la primera vez y, en ejecuciones posteriores, devuelve ese mismo valor computado al principio.  
A su vez, existe una variante de `remember{}` que es un delegado de propiedades (ver item 2.5 sobre *Delegated properties* en [Apuntes de Kotlin](https://github.com/Ulises-Jota/Apuntes-y-Navaja-Suiza/blob/master/Apuntes-Kotlin.md#2-propiedades-y-fields-en-kotlin)). De esta forma, se puede evitar usar el `.value` para recuperar el valor donde se requiere.  
Sin embargo, cuando la *activity* se destruye y recrea (por ejemplo, al rotar el dispositivo), ese estado se pierde. Para evitar esto y guardar el estado en esos casos, se utiliza ***`rememberSaveable{}`***. Hay que tener en cuenta que esta variante utiliza el mismo `Bundle` que la *activity*, por lo que el tipo del valor a almacenar debe ser alguno de los soportados por los `Bundle`. Para solucionar eso, se le puede pasar como parámetro un objeto de tipo ***`Saver`*** que, dado un valor, lo convierte a un `Bundle` y eso se puede almacenar.  
Pero puede pasar que otras vistas necesiten un mismo estado, o que se vaya a utilizar en otras partes de la aplicación, o cuando se quiere que el *ViewModel* gestione el estado. Para esos casos, existe una técnica conocida como ***State hoisting***, que consiste en "extraer" ese estado de las vistas y que el control del mismo recaiga en otro elemento, de jerarquía superior. En vez de crear el estado dentro del `Composable`, se sustituye por dos argumentos: uno proporciona el valor y el otro es un *lambda* que modifica ese valor.


#### 8. ***AppBar*** y ***Scaffold***
Lo primero que se debe hacer, es ir al ***Manifest*** y asegurarse que el ***theme*** de la *activity* no tenga (o herede de un tema que no tenga) ***ActionBar***.  
    Por ejemplo: `<style name="Theme.MyMovies" parent="android:Theme.Material.Light.NoActionBar">`  

Al igual que en los *xml*, la *AppBar* se puede colocar en cualquier parte de la pantalla. Pero si se quiere usar en la parte superior de la pantalla (lo habitual), lo ideal es usar el `Composable` ***`Scaffold`***. Este componente permite posicionar elementos típicos de *Material* en sus posiciones habituales sin necesidad de hacer nada extra.  
***`Scaffold`*** es el ejemplo perfecto de un patrón que se repite en ***Jetpack Compose***, llamado ***Slot API***. Este patrón consiste básicamente en que el componente ofrece huecos o *slots* donde se puede añadir lo que uno quiera (*lambdas* genéricos que aceptan contentido *composable*). Ref.: [Practical Compose Slot API example](https://www.valueof.io/blog/compose-slot-api-example-composable-content-lambda)  
Por ejemplo, el parámetro `title` de `TopAppBar` no obliga a que deba contener un `Text` sí o sí. Bien podría contener una `Row` con un texto, un `Spacer` y un ícono:  

````kotlin
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Row {
                                    Text(text = stringResource(id = R.string.app_name))
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Icon(imageVector = Icons.Default.Android, contentDescription = null)
                                }
                            })
                        }
                    )
````  

También se puede agregar un **ícono de navegación** de forma muy simple con un ***`IconButton`*** (un botón que contiene un ícono):

````kotlin
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = stringResource(id = R.string.app_name)) },
                                navigationIcon = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )
                        }
                    )
````  

También es posible agregar **acciones de menú**:

````kotlin
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = stringResource(id = R.string.app_name)) },
                                actions = {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = null
                                        )
                                    }
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Default.Share,
                                            contentDescription = null
                                        )
                                    }
                                }
                            )
                        }
                    )
````  


#### 9. Estructurar el código en ***Jetpack Compose***
Algunas recomendaciones a tener en cuenta a la hora de estructurar el código en ***Jetpack Compose***:  
  - **Crear un ``Composable`` con la base de la aplicación:**  
    Lo ideal es tener un ``Composable`` que defina las configuraciones y reutilizarlo en todos lados. Es importante recordar que **las funciones `Composable` deben devolver `Unit`, ya que estas funciones emiten componentes de UI**, pero no devuelven nada.
  - **Dividir el ``Composable`` en otros más pequeños:**  
    Los ``Composables`` deberían ser auto-explicativos y tener nombres semánticos.
  - **Crear ``Composables`` que definan las pantallas:**  
    Lo ideal, es crear ``Composables`` para definir cada una de las pantallas.
  - **Estructurar los paquetes de UI por pantallas:**  
    El código queda más ordenado, puede crecer de forma más extensible y se podrán crear tanto *feature* como ``Composables`` sin que se vuelva un desorden.
  - **Extraer las dimensiones:**  
    *Hardcodear* las dimensiones va a representar un problema si se quiere **configurar la aplicación para distintos tamaños de pantalla (dispositivos diferentes)**. Para eso, es preferible extraerlos al archivo ***dimens*** y obtenerlos con el método ``dimensionResource``.


#### 10. Navegación básica con ***`Navigation Compose`***
El componente de navegación de *Jetpack* (ver apunte sobre [Navigation component](https://github.com/Ulises-Jota/Apuntes-y-Navaja-Suiza/blob/master/Apuntes-Android.md#navigation-component)), provee soporte para las aplicaciones con ***Jetpack Compose***. Algo a tener en cuenta es que en ``Navigation Component`` se puede utilizar un diseñador para generar el grafo de navegación, pero esto no es posible con ``Navigation Compose``. En este caso, se realiza mediante código, con un DSL.  
Para usar ``Navigation Compose``, se debe agregar la librería correspondiente: `implementation "androidx.navigation:navigation-compose:<VERSION>"`. Una vez incluida la dependencia, se debe configurar `Navigation Compose` para poder utilizarlo:  
Lo primero que se necesita, es un `NavHostController`. Para que no se duplique este controlador, se usa una función llamada `rememberNavController` (ver item sobre el [estado](#7-el-estado-en-jetpackcompose)).  
Lo segundo que se necesita, es el ``NavHost``. Es un componente de `Navigation Compose` que va a definir el grafo de navegación, en donde se le indicará qué *NavController* tiene que usar; como también la pantalla de origen y las pantallas a las que se puede navegar (ambas `Composables`). Utiliza una función llamada `composable()` a la que se le indica **la ruta de navegación y el `Composable` que debe pintarse** cuando se esté en esa ruta, como también los argumentos con `navArgument`.
Lo tercero que se necesita, es recuperar los argumentos para poder propagarlos. Para eso, en el *lambda* de la función ``composable()``, se usa el `NavBackStackEntry` para acceder a los mismos.

````kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") {
                    MainScreen(navController)
                }
                composable(
                    route = "detail/{mediaId}",
                    arguments = listOf(
                        navArgument("mediaId") { type = NavType.IntType }
                    )
                ) { navBackStackEntry ->
                    val id = navBackStackEntry.arguments?.getInt("mediaId")
                    requireNotNull(id, { "No puede ser nulo porque el detalle siempre necesita un ID" })
                    DetailScreen(id)
                }
            }
        }
    }
````


#### 11. Uso de ***Cards*** de ***Material Design*** en ***Jetpack Compose***
Lo único que se necesita, es rodear el componente que uno quiera con el componente de [***Cards*** de ***Material Design***](https://m3.material.io/components/cards/overview).


#### Referencias extra
- [Practical Compose Slot API example](https://www.valueof.io/blog/compose-slot-api-example-composable-content-lambda)
