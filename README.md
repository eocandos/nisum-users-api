# Prueba Técnica Java para NISUM - API RESTful de creación de usuarios.
### Autor: Erick Ocando - Fecha: 12/2021

### Tecnologías

- Java 11
- Spring Boot - version 2.6.2
- JPA
- Hibernate
- H2
- Maven
- Autenticación con Token JWT
- Docker

### Funcionalidades

1. Funcionalidad Crear Usuario y retorno de token de la sesión
2. Funcionalidad Consultar Usuario y recibir información: 
   1. Id
   2. Fecha creación
   3. Fecha actualización
   4. Token sesión
   5. Teléfonos
   6. Entre otras
3. Validación y restricción si el correo a registrar ya existe
4. Validación y restricción expresión regular para registro de correos (aaaaaaa@dominio.cl)
5. Validación y restricción expresión regular en la contraseña para validar formato correcto
6. Persistencia del token junto a la información del Usuario
7. Formato en los mensajes: {"mensaje": "mensaje de error"}
8. Funcionalidad adicional actualizar Usuario
9. Ejecución con maven o con Docker

### Diagrama Autenticación empleado (Usando token JWT)
![create-user](./github_images/jwt-diagram.png)

### Estructura Proyecto
![create-user](./github_images/project-struct.png)

### Ejecución de la Aplicación
#### Ejecución con maven y Java (Necesario maven y java 11 instalados) 

- clonar el proyecto ```$ git clone https://github.com/eocandos/nisum-users-api-0.1.git ```
- Ir al directorio del proyecto
```$ cd nisum-users-api/nisum-users-api-0.1/ ``` 
- Generar jar del proyecto
```$ mvn clean && mvn install ```
- Correr proyecto con Maven ```$ mvn spring-boot:run  ```
- O con java ```$ java -jar target/nisum.users.api-0.0.1-SNAPSHOT.jar ```

#### Ejecución con Docker (Necesario docker instalado)

- clonar el proyecto ```$ git clone https://github.com/eocandos/nisum-users-api-0.1.git ```
- Ir al directorio del proyecto
  ```$ cd nisum-users-api/nisum-users-api-0.1/ ```
- Generar jar del proyecto ```$ mvn clean && mvn install ```
- Crear imagen ```$ docker build -t nisum-users-api:v0.1 . ``` *Si es un sistema operativo linux a lo mejor deba 
ejecutar este comando como administrador, es decir, con sudo ```$ sudo docker build -t nisum-users-api:v0.1 .```
- Ejecutar imagen creada  ```$ sudo docker run -p 8080:8080 nisum-users-api:v0.1 ``` 

### Realizar Peticiones con Postman

#### Importar colección en Postman

- Dentro de la carpeta postman-collections encontraremos las colecciones de postman para realizar las peticiones a la api

![create-user](./github_images/folder-postman.png)
- Abrimos postman y en archivos/importar importamos la colección que queramos: 
- Probar la app en local o apuntarle a una instancia de AWS
- Luego de importar las colecciones procedemos a realizar peticiones a la API:

#### En local

- Crear Usuario | Abrimos el endpoint en la colección para crear un nuevo usuario
![create-user](./github_images/create-user-local.png)
- Tomamos el token que nos regresa la API luego de la autenticación para proceder con las siguientes peticiones
![token](./github_images/token.png)
- Obtener usuarios | Abrimos el endpoint para obtener los usuarios registrados y establecemos el header de la solicitud usando el token que nos entregó el API
![users-registered](./github_images/set-token-get-user.png)
- Realizamos la petición de obtener todos los usuarios registrados
![users-registered](./github_images/get-users.png)
- Validación y restricción si el correo a registrar ya existe
![users-registered](./github_images/emai-exist.png)
- Validación y restricción expresión regular para registro de correos (aaaaaaa@dominio.cl)
![users-registered](./github_images/invalid-email.png)
- Validación y restricción expresión regular en la contraseña para validar formato correcto
![users-registered](./github_images/invalid-password.png)
- Funcionalidad adicional actualizar Usuario
![users-registered](./github_images/update-user.png)
![users-registered](./github_images/get-after-edition.png)

#### Instancia AWS

Como adición se muestra la API corriendo en una instancia de EC2 de AWS para usarla como ambiente de pruebas

Podemos realizar las mismas peticiones pero ahora usando la otra colección que está en la misma carpeta (postman-collections) pero la que se llama NISUM-USER-API (AWS-INSTANCE)
- Crear Usuario
![users-registered](./github_images/create-user-remote.png)
- Obtener Usuarios registrados (No olvidemos tomar el token activo e incluirlo en las cabeceras de la petición)
![users-registered](./github_images/get-users-remote.png)