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
Además posee el método isKO() que entrega un Boolean que indica si el aventurasMarcoyLuis 
esta con 0 de vida o no. 

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

Finalmente el paquete controller donde esta el contrador de una Batalla BattleController 
la cual está encargada
de poseer todos los métodos necesarios para poder ejecutar y controlar un partida y batalla.
Tambien la clase PlayerIn que se ocupa para guardar un player y su entrada para hacer las 
intereccaciones en la batalla y poder elejir acciones. GameController que simplemente ejecuta
una batalla completa llamando al método update de BattleController. 

Además dentro de este último paquete se crea la clase NullOutputStream para en los tests no
printear nada aunque se llame a print. 



