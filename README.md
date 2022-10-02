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

