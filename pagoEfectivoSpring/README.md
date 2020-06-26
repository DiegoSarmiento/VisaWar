# Configuracion, Dependencias, Software Utilizado

###Software Utilizado

+ `org.springframework.boot`, la versión utilizada es 2.3.1 RELEASE.
+ `JAVA`, Versión de Java es la 8.

###El presente proyecto tiene como depencias

+ `spring-boot-starter-thymeleaf`, Template utilizado para las vistas.
+ `spring-boot-starter-web`, Inicio para crear web, incluido RESTful, aplicaciones que usan Spring MVC. Utiliza Tomcat como contenedor incrustado predeterminado
+ `org.springframework.boot`, Starter para probar aplicaciones Spring Boot con bibliotecas como JUnit, Hamcrest y Mockito.
+ `com.google.code.gson`, Gson es una biblioteca de código abierto para el lenguaje de programación Java que permite la serialización y deserialización entre objetos Java y su representación en notación JSON.
+ `spring-boot-devtools`, Es la herramienta de Spring Boot que nos permite reiniciar de forma automática nuestras aplicaciones cada vez que se produce un cambio en nuestro código.
+ `org.springframework.web.client.RestTemplate`, Cliente sincrónico para realizar solicitudes HTTP, exponiendo una API de método de plantilla simple sobre bibliotecas de cliente HTTP subyacentes como JDK, Apache HttpComponents y otros.


###Configuración

+ Las credenciales de configuración se encuentran en la ruta src/main/resources/static en el archivo `configura.json`.
+ Para las compilaciones se ha utilizado Apache Maven 3.6.3.
