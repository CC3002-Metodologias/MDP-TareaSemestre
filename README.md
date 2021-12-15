# Aventuras de Marco y Luis

#
Este proyecto trata de crea un clon simplifacado del combate de los
de la saga Mario and Luigi desarrollado por AphaDream Corporation. 

### Modelo
Dentro del paquete aventurasMarcoyLuis, tenemos el paquete abstract_clases en donde 
se encuentran 3 clases abstractas, descritas a continuación. AbstractPersonaje
el cual es la clase padre de todos los personajes creados tanto enemigos como 
personajes principales (players), clase que tiene como variables las 
estadisticas de cada aventurasMarcoyLuis y métodos para poder manejar estas mismas.

Además tenemos AbstractEnemy y AbstractPlayer que extienden la clase 
AbstractPersonaje y se usan para definir comportamientos y métodos en común
que usarán los enemigos y players respectivamente. Cabe destacar que 
dentro de AbstractPlayer añade 2 nuevas variables y añade metodos para 
poder utilizar los 3 items que de momento existen. 

Poseemos además el paquete interfaces donde se encuetran creadas dos interfaces
ipersonajes y ienemy implementadas por AbstractPlayers y AbstractEnemy
respectivamente, que son usados por el momento para definir metodos para 
el ataque entre personajes y también poder llamar a todos los enemigos y players 
en funciones sin tener que especificar el aventurasMarcoyLuis puntual. Además la interfaz Items
que se ocupa para implementarse en RedMushroom y HoneySyrup

Tenemos el paquete Items donde estan las clases RedMushroom y HoneySyrup.

El paquete personjes donde se encuentran las clases que hacen referencia a cada personaje en 
particular. Luis y Marco que son los personajes principales y extienden 
la clase AbstractPlayers. Boo, Goomba y Spiny enemigos que extienden la 
clase AbstractEnemy. A cada uno de estos se le dan estadísticas propias en 
las variables de su clase. 

AttackType que es un enum de los dos tipos de ataques que existen.


### Controlador

El controlador esta divido en 2 clases: 

-GameController: controla todos los aspectos del juego en general. Este puede crear Items y 
personajes, realizar una batalla, controlar los turnos, obtener información relevante en la 
partida y llamar a realizar las acciones de los personajes principales. Además cuenta con 
diversas variables como un Baúl de objetos, listas de personajes en una ronda, lista de 
PlayerIn's en el juego, una Fase, Observadores, y diversas variables que indican información 
acerca de la partida. 

-PlayerIn: controla a un personaje principal (Iplayer). Este puede hacer toma de decisiones 
para elegir una acción a realizar, elegir un item, un tipo de ataque y una victima dentro de una 
lista de enemigos. Permite recibir un Input en formato de String para que el usuario interactue.

Además dentro de este último paquete se crea la clase NullOutputStream para en los tests no
printear nada aunque se llame a print.

### Fases y exepciones

Las fases representan transiciones y estados del juego. Estas son útiles para que cuando el 
controlador se conecte con la interfaz gráfica, y un usuario interactue de manera incorrecta 
cuando se decide una acción, una opción, etc; el controlador tenga forma de intentar realizar 
cierto acción, comprobando primero si esta en una fase correcta, y en caso contrario no se caiga 
el programa. 

Además incorporamos 4 nuevos clases de Exepciones, para diversos escenarios en donde se intenta
realizar una acción no valida o incorrecta. InvalidOptionException se arroja cuando se intenta 
un metodo a nivel de la fase y controlador, que no es permitida en esa fase en concreto. 
InvalidInputException se arroja cuando el usario ingresa un input que no se asocia con ninguna 
opción válida. InvalidTransitionException cuando una se quiere transicionar desde una fase a otra 
que no es permitida por la primera. Finalmente InvalidAttackException que se arroja cuando 
un personaje quiere atacar a otro y no es valido dado los tipos de personajes involucrados o el tipo 
de ataque que se quiere utilizar para atacar a un enemigo. 

### Observers y visitores

Se tiene además 2 tipos de observers que usa el controlador para poder revisar momentos claves y 
efectuar las acciones correspondientes. 

También se crea un visitor para poder obtener todos los personajes atacables por boo dentro 
de una lista de personajes. Se deja también la base hecha para expansiones del código y puedan 
ser creados nuevos visitores para usos similares. 


### Intrucciones 

Solo selecciona el test deseado y ejecútalo.




