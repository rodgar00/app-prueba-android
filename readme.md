# Índice
- [CLASES](#clases)
  - [MainActivity](#mainactivity)
  - [FormUtils](#formutils)
  - [Login](#login)
  - [Register](#register)
- [XML](#xml)
  - [AndroidManifest](#androidmanifest)
  - [fade_in](#fade_in)
  - [activity_main](#activity_main)
  - [activity_login](#activity_login)
  - [activity_register](#activity_register)
  - [activity_splash](#activity_splash)
-[Temas](#Temas)

# CLASES 

## MainActivity
La mainActivity es una pantalla de bienvenida con un contador. Al arrancar, en el onCreate se activa el edgeToEdge, se carga el layout activity_main y se ajustan los márgenes con los insets.

Luego obtenemos las referencias a los elementos del layout: el botón, el textview donde se muestra el contador y el textview de bienvenida. Después recogemos el bundle que llega del intent anterior (desde login o register) y se extrae el nombre del usuario que se pasó como extra. Con eso se cambia el texto del mainBienvenidaTV para mostrar “hola [nombre], bienvenido”, que es el mensaje de bienvenida personalizado.
Se inicializa el contador en 0, se muestra ese valor en el mainTV, y se añade un onclicklistener al botón. cada vez que se pulsa, el contador se incrementa en uno y se actualiza el texto del mainTV con el nuevo valor.

![img.png](img.png)
## FormUtils
Esta clase se encarga de todas las validaciones y utilidades relacionadas con los formularios. Primero tiene un método para comprobar si el email es correcto usando una expresión regular para que el usuario meta un correo real.
Después hay otro método que comprueba si un TextInputLayout (TIL) está vacío. Si lo está, devuelve true, y así el resto del código puede marcar el error fácilmente sin tener que repetir la misma comprobación en cada campo.
También tiene el método para generar una contraseña hasheada, de forma que nunca se guarde en texto plano. Esto sirve para añadir una capa de seguridad por si alguien accede a las SharedPreferences.
Otro método compara dos contraseñas para ver si son iguales, en el Register cuando el usuario confirma la suya.
Hay funciones que validan si el nombre de usuario introducido en el login coincide con el guardado en las preferencias durante el registro, o si el email cumple con el formato correcto.

![img_1.png](img_1.png)
## Login
En este login lo primero que se hace en el onCreate es configurar la pantalla, activar el edgeToEdge para que la interfaz se vea ajustada a los bordes y luego se carga el layout activity_login. después se prepara el listener de los insets para que los márgenes se adapten a los bordes del sistema y no se corte nada.

Declaramos las variables que representan los elementos de la vista, el botón para hacer login, el texto de “¿no tienes cuenta?” para ir al registro, y los dos textinputlayout del usuario y la contraseña.
Luego añadimos los textwatcher, que reaccionan cada vez que el usuario escribe algo. en este caso se usan para que si la contraseña o el nombre están vacíos, se muestre un error debajo del campo. Si hay texto, se quita el error. Esto se hace en el afterTextChanged, el resto de métodos del textwatcher no se usan.

Después se inicializa la clase formutils, que se encarga de validar los datos, y se recuperan los datos guardados en las sharedpreferences: el nombre de usuario y la contraseña cifrada que se habían guardado antes en el registro.
Al pulsar el botón de login se crea un booleano canContinue, que sirve para controlar si se puede pasar a la siguiente pantalla o no. Ahí dentro se hacen todas las comprobaciones, primero si el nombre está vacío, si lo está se marca error y canContinue pasa a false. Luego se comprueba si el nombre coincide con el guardado. Si no coincide, error otra vez. Lo mismo para la contraseña, se mira si está vacía, y si no, se comprueba si coincide con la guardada. Si alguna de esas validaciones falla, canContinue se pone a false y se queda ahí.
Solo si canContinue sigue siendo true, significa que todo está correcto, entonces se muestra un toast con “bienvenido” y el nombre que ha escrito el usuario, y se crea un intent para ir a la mainActivity. se mete el nombre en un bundle para poder pasarlo a la siguiente actividad y luego se ejecuta startActivity para cambiar de pantalla.
Abajo está el listener del texto que dice “¿Aún no tienes cuenta?”. si el usuario pulsa ahí, se crea un intent que lleva a la pantalla de register para que se cree la cuenta.

## Register
Aquí pasa algo muy parecido al login, solo que en lugar de comprobar credenciales, se crean y guardan. Al empezar, en el onCreate se activa el edgeToEdge, se carga el layout de activity_register y se ajustan los márgenes de la vista para que todo quede bien con la barra del sistema.

Luego declaramos los textinputlayout del nombre, email, contraseña y confirmación de contraseña, el botón para registrarse y el texto de “¿ya tienes cuenta?” que lleva de vuelta al login. también se preparan las sharedpreferences junto a su editor, que es donde se van a guardar los datos del usuario.
Cada campo tiene su propio textwatcher. el del nombre comprueba si está vacío y muestra un error si lo está. El del email usa el método de formutils isEmailCorrect, que valida el formato del correo; si no cumple, se muestra “tu email es inválido”. El de la contraseña hace lo mismo, muestra error si el campo está vacío. todo esto se hace en el afterTextChanged, que se ejecuta justo después de que el usuario cambie el texto.

Cuando se pulsa el botón de registro, se crea un booleano canContinue igual que en el login. Se usa para decidir si se puede seguir o no. Primero comprobamos si el nombre está vacío, luego si el email está bien escrito, y por último si las contraseñas coinciden. Si las contraseñas no son iguales, se marca error en el campo de confirmación y se lanza un toast diferente según el caso: si no se ha escrito nada, avisa que el campo está vacío, y si se ha escrito pero no coinciden, avisa que las contraseñas no coinciden.
Solo si todas las comprobaciones pasan, es decir si canContinue sigue siendo true, se guardan los datos en las sharedpreferences: el nombre, el email y la contraseña ya cifrada con generateHashedPassword. después muestra un toast diciendo que el registro ha sido exitoso junto con el nombre, crea un intent hacia la mainActivity, mete el nombre del usuario en un bundle y cambia de pantalla con startActivity.
Al final, el texto que dice “Ya tienes cuenta” tiene su listener que simplemente crea un intent hacia la pantalla de login, por si el usuario quiere volver al inicio de sesión.


# XML

## AndroidManifest
Este AndroidManifest es el archivo principal de configuración de la aplicación, donde se declara todo lo que el sistema Android necesita saber antes de ejecutarla.

Dentro del bloque <application> se definen los ajustes generales de la app: el icono (@drawable/cat), el nombre que se mostrará (@string/app_name) y el tema (@style/AppTheme).
Después se listan todas las actividades que tiene la aplicación: **Register**, **Login**, **MainActivity** y **SplashScreen**. La **SplashScreen**, es la pantalla inicial, lleva el intent-filter con las etiquetas <action android:name="android.intent.action.MAIN"/> y <category android:name="android.intent.category.LAUNCHER"/>, que le dicen al sistema que esa es la primera actividad que se ejecuta al abrir la aplicación.
Este archivo conecta todo el proyecto con el sistema Android: define permisos, iconos, temas y, sobre todo, el orden en el que las actividades se abren cuando el usuario inicia la app.

## fade_in
Este archivo fade_in.xml define una animación de tipo alpha, que controla la transparencia de un elemento. 

Hace que una vista aparezca poco a poco en pantalla en lugar de mostrarse de golpe. El valor fromAlpha 0.0 indica que empieza totalmente transparente, y toAlpha 1.0 que acaba siendo completamente visible. el interpolator accelerate_interpolator hace que la animación empiece despacio y vaya acelerándose hasta el final, y la duración de 1000 significa que tarda un segundo en completarse.
Al entrar al programa lo podemos ver.

## activity_main
Este XML define la interfaz de la pantalla principal (MainActivity).

Primero hay un TextView con el id mainBienvenidaTV. Muestra el mensaje de bienvenida que se genera en el código Java usando el nombre del usuario recibido con el bundle. Está centrado encima del contador.
Luego hay otro TextView con el id mainTV. Ese es el que muestra el número del contador que empieza en cero y se incrementa cada vez que se pulsa el botón.
Por último está el botón mainButton con el texto “Sumar”. Al pulsarlo, ejecuta el listener definido en MainActivity que incrementa la variable contador y actualiza el TextView mainTV con el nuevo valor.

## activity_login
Este layout activity_login.xml define la interfaz de la pantalla de inicio de sesión. Todo está dentro de un ConstraintLayout, que deja posicionar los elementos con guías y restricciones en lugar de márgenes fijos, así el diseño se adapta mejor a diferentes pantallas.

Arriba hay una Guideline horizontal al 20% de la altura, que sirve para dejar espacio superior y alinear el contenido. Justo debajo se coloca el logo con una imagen del drawable cat. Debajo del logo está el nombre de la app con un TextView y una fuente personalizada que se llama brich. Luego hay dos guías verticales al 20% y al 80% del ancho, que marcan los límites izquierdo y derecho. Entre esas guías se colocan los campos de texto. El primer TextInputLayout es para el nombre de usuario, tiene un icono de persona a la izquierda y dentro lleva un EditText con hint “Nombre de usuario” y tipo de entrada de texto normal. Debajo de ese campo está el segundo TextInputLayout para la contraseña, con un icono de llave, el toggle de mostrar o ocultar contraseña activado, y un EditText que pide “Contraseña”. Después de los campos está el botón de login, centrado y con texto “Entrar”. Debajo hay un TextView con la frase “¿Aún no te has registrado?” en cursiva, que sirve para que el usuario pueda ir a la pantalla de registro *(Register)* si todavía no tiene cuenta.

## activity_register
Este xml define la interfaz del registro de usuario. Primero se coloca una guideline horizontal arriba (registerGLH1) para marcar un margen superior del 15% de la pantalla, así el contenido no queda pegado arriba. Debajo está el imageView con el logo del gato y justo debajo un textView con el título “registro” con una fuente personalizada (brich). 
Luego hay dos guías verticales (registerGLV1 y registerGLV2) que delimitan los márgenes izquierdo y derecho de los campos de texto, dejando el formulario centrado. Después vienen los campos de entrada, todos envueltos en TextInputLayout de material design, que añaden iconos y mejoran la apariencia. El primero (registerTILusername) pide un nombre de usuario y muestra el icono de persona. El segundo (registerTILemail) pide un correo electrónico con el icono de email. El tercero (registerTILpassword) es para crear una contraseña y tiene la opción de mostrarla o esconderla (passwordtoggleenabled). El cuarto (registerTILpasswordconfirm) sirve para confirmar la contraseña con la misma configuración. Debajo está el botón principal (registerButton) que pone “registrarse”, centrado y separado con un margen superior. Finalmente hay un textview (registerTVlogin) para el usuario vaya a iniciar sesión si ya tiene cuenta.

## activity_splash
Este xml define la pantalla inicial SplashScreen. splashImagenFondo es la imagen de fondo que ocupa toda la pantalla. Encima está splashDegradado, una vista con fondo degradado que oscurece el fondo. splashLogo es el icono principal de la aplicación, centrado horizontalmente usando la guía splashGLh1. Tiene la imagen del gato. Debajo de él está splashAppName, un TextView que muestra el texto “Bienvenido”, con una fuente personalizada (brich) y color primario, también centrado. splashGLh1 es una Guideline horizontal en el 0% que sirve como punto de referencia superior para el logo.
En la clase SplashScreen, se carga este layout y se aplica la animación fade_in al splashLogo y al splashAppName para hacer que aparezcan suavemente y de movimiento. Después de un pequeño retraso, la actividad lanza un Intent hacia Register o Login según el flujo de la app.

# Temas
En /app/src/res encontramos values y values_night con el tema.
