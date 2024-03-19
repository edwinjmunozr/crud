

# CRUD Service API

Este proyectos es una aplicación que expone una **API Restful** para la creacion de usuarios.

Este proyecto está basado en el siguiente documento [TallerJava](/docs/ejercicio_java.pdf), luego todas las funcionalidades que no estan implicitas en el documento se han adicionado como mejoras al proceso.

## Como usar la aplicación

Para poder usar los endpoint que expone la API para el manejo de usuario (**/api/users**) se debe seguir los siguientes dos pasos:

1.- Primero se debe autenticar como usuario. Por defecto cuando se inicia la aplicacion se crea el usuario **admin** el cual tiene email: **admin@youdomain.com** y clave: **Colombia123#**

La siguiente imagen muestra la forma como se realiza la peticion.

 ![Login Request](/docs/login_request.png)


En esta imagen observamos la formo como se haria utilizando postman.
 ![Login Request postman](/docs/login_ok.png)


2.- Utilizar el **token** que se retorna en el paso anterior y usarlo en la cabecera como medio de autenticacion en las siguientes peticiones (request).

![Requet example](/docs/get_all_users.png)


## Documentación

La documentacion del proyecto la encuentra en la url relativa **/api/swagger-ui/index.html**
[**Swagger**](http://localhost:8080/api/swagger-ui/index.html)


 ![API](/docs/swagger.png)

 
## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

**`EMAIL_PATTER`**: esta variable permite definir la expresion regular (regexp) que se utilizará para validar el formato del correo.

**`PASSWD_PATTER`**: esta variable permite definir la expresion regular (regexp) que se utilizará para validar el formato o conetenido del password al momento de crear al usuario.

**`JWT_KEY`**: esta variable define la clave privada que se utilizará para firmar los token jwt generados por la aplicacion.


## Deployment

Para ejecutar el proyecto por favor siga los siguientes pasos:

1.- Instale, actualice y/o valide que tenga instalado docker.

2.- Descargue el codigo fuente del repositorio.

3.- Cree la imagen docker del proyecto.

```bash
  docker build -t crud:1.0 . [ENTER]
```

4.- Ejecute o lance el contenedor docker con la imagen que acaba de generar

```bash
  docker run -d -it --name apicrud -p 8080:8080 crud:1.0 [ENTER]
```

5.- Espere unos segundos mientras se crea el contenedor y luego que el servicio este funcionando. Una forma de hacerlo es revisando si la documentacion del proyecto esta habilitada: [**Swagger**](http://localhost:8080/api/swagger-ui/index.html)


## API Reference

#### Add new user

```http
  POST /api/users
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Authorization` | `string` | **Required**. JWT (Header)|

La siguiente imagen muestra una peticion completa:
 
 ![AddUser](/docs/users_add.png)

**Nota**: La aplicación no permite la duplicidad del email de los usuario.

 ![AddUser Error](/docs/users_add_error_email.png)

#### Get all users

```http
  GET /api/users
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Authorization` | `string` | **Required**. JWT (Header)|


 ![All Users](/docs/get_all_users.png)

#### Get user

```http
  GET /api/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Authorization` | `string` | **Required**. JWT (Header) |
| `id`      | `string` | **Required**. Id of user to fetch |

![User by Id](/docs/users_find_by_id.png)

## Tech Stack


![Technologias](/docs/technologies.png)


## MER Diagram

El Modelo Entidad Relación (MER) que se utiliza es el siguiente. 

![Diagrama MER](/docs/crud_users_mer.png)


## Diagrama de paquetes

La siguiente imagen muestra el diagrama de conexion entre los paquetes que component en proyecto.

![Estructura del Proyecto](/docs/project_structure.png)


![Paquetes](/docs/packages.png)


 ## Screenshots

![Validacion](/docs/password_validation_1.png)


## Authors

- [@EdwinJMunoz](https://www.github.com/edwinjmunoz)


## Feedback

If you have any feedback, please reach out to us at edwinjmunoz@gmail.com



