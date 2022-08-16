# MyMovies
Primeros pasos con Jetpack Compose (curso Antonio Leiva)

----

### Algunos apuntes

1°  
¿Qué es Jetpack Compose?
Es un sistema de creación de interfaces declarativas para Android.

`New Project` -> `Empty Compose Activity`


2°  
**BOX**: El FrameLayout de Compose.  
**COLUMN**: El LinearLayout vertical de Compose.  
**ROW**: El LinearLayout horizontal de Compose.


3°  
**MODIFIER**: Permite añadir apariencia o capacidades extra a un `Composable`. Es un `companion object` de una interfaz con el mismo nombre. Tiene un API de tipo *Builder*. Es importante el orden: se van a aplicar en el orden que se hayan definido.

Hay varios tipos de modificadores:  
- De posicionamiento y tamaño: Indica cómo se va a posicionar una vista respecto a las vistas con las que interactúa, así como el espacio que ocuparán en pantalla. Ejs.: `fillMaxWidth`, `width`, `height`.  
- De funcionalidad: Permiten ampliar características sobre el `Composable` en el que se aplican. Ejs.: `clickable`, `horizontalScroll`, `draggable`.  
- De apariencia: Ejs.: `background`, `padding` (en Compose, sólo existe el `padding`, no hay `margins`), `scale`, `border`, `alpha`.  
- Listeners: Permiten escuchar ciertos eventos relacionados con la vista. Ejs.: `onFocusChanged`, `onKeyEvent`, `onSizeChanged`.  

4°  
**TEXT**: Es el `Composable` encargado de renderizar el texto y el equivalente al TextView. Existe una versión más básica llamada *BasicText*, pero mayormente no se utiliza ya que no aplica las guías de *Material Design*.  
En el archivo ***Type*** detro de ***ui.theme*** se definen las tipografías de *Material* que usará el Tema en ciertos puntos de la aplicación, como también en el Estilo de los textos que se necesite.  

