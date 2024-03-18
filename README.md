

# CRUD Service API

Este proyectos es una aplicación que expone una **API Restful** para la creacion de usuarios.

Este proyecto está basado en el siguiente documento [TallerJava](/docs/ejercicio_java.pdf), luego todas las funcionalidades que no estan implicitas en el documento se han adicionado como mejoras al proceso.

## Como usar la aplicación

Para poder usar los endpoint que expone la API para el manejo de usuario (**/api/users**) se debe seguir los siguientes dos pasos:

1.- Primero se debe autenticar como usuario. Por defecto cuando se inicia la aplicacion se crea el usuario **admin** el cual tiene email: **admin@youdomain.com** y clave: **Colombia123#**

 ![Login Request](/docs/login_request.png)

 ![Login Request postman](/docs/login_ok.png)


2.- Utilizar el **token** que se retorna en el proceso anterior y usarlo en la cabecera de autenticacion en las siguientes peticiones (request).

![Requet example](/docs/get_all_users.png)


## Documentation

La documentacion del proyecto la encuentra en la url relativa **/api/swagger-ui/index.html**
[**Swagger**](http://localhost:8080/api/swagger-ui/index.html)


 ![API](/docs/swagger.png)

## API Reference

#### Get all items

```http
  GET /api/users
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/users/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## Tech Stack

**Client:** React, Redux, TailwindCSS

**Server:** Node, Express


## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

**`EMAIL_PATTER`**: esta variable permite definir la expresion regular (regexp) que se utilizará para validar el formato del correo.

**`PASSWD_PATTER`**: esta variable permite definir la expresion regular (regexp) que se utilizará para validar el formato o conetenido del password al momento de crear al usuario.

**`JWT_KEY`**: esta variable define la clave privada que se utilizará para firmar los token jwt generados por la aplicacion.

`API_KEY`


## Deployment

To deploy this project run

```bash
  npm run deploy
```


## Installation

Install my-project with npm

```bash
  npm install my-project
  cd my-project
```
    
## Screenshots

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)


## Authors

- [@EdwinJMunoz](https://www.github.com/edwinjmunoz)


## Feedback

If you have any feedback, please reach out to us at edwinjmunoz@gmail.com



