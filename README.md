# Prices-api

## Table of Contents

* [Informacion general](#Informacion-General)
* [Tecnologias](#Tecnologias-Usadas)
* [Features](#features)
* [Usos](#usos)
* [Setup](#setup)
* [Contacto](#contacto)

## Informacion General

El proyecto consiste en una api que devuelve el precio final de venta en funcion de los parametros enviados.
Esta aplicación se ha desarrollado siguiento la arquitectura hexagonal quedando la estructura de carpetas de la
siguiente
manera:

- ### Application
    - Será la responsable de la lógica de dominio.
        - mapper : Se encuentra la clase que mapea de la entidad de dominio a la entidad que se devolverá por
          controlador.
        - port.in : Se encuentran las interfaces que se usarán en los servicios internos.
        - service : Se encuentra la lógica del servicio interno.
- ### Domain
    - Contiene las clases que representan el dominio de la aplicación, como entidades.
        - Se encuentra el modelo que se usa para la logica interna de negocio, para añadir una capa extra de seguridad.
- ### Infraestructure
    - Será la capa encargada de recibir y mandar mensajes a servicios externos, por ejemplo el controlador o la bbdd.
        - controller : Se encuentra el controlador de la api y su endpoint.
        - dto : Se encuentra el modelo que se devolvera desde el controlador.
        - mapper : Se encarga de mapear de la entidad del repository a la entidad de la lógica de negocio.
        - repository
            - entity : El modelo que tiene la relacion con la bbdd y que usará JPA.
            - h2 : La interfaz que tiene la query para la bbdd y sacar los respectivos datos.



## Tecnologias Usadas

- SpringBoot 2.7.0
- Version de java: 11
- Spring Data JPA: para mapear objetos de la base de datos.
- Spring Web: para poder implementar @RestController y simplificar la creacion de la api.
- H2 Database: para poder crear una base de datos en memoria.
- Lombok: para que las entidades se queden más limpias y simplificar el codigo.
- Mapstruct: para realizar mapeos de entidades.
- Apache log4j: para poder usar logs en la aplicación y debuggear.
- JaCoCo: para ver los reportes de los test ejecutados.

## Features

Actualmente solo tiene una feature, la cual es recuperar el precio en base a los siguientes parametros:

```
applicationDate de tipo LocalDateTime.
productId de tipo Integer.
brandId de tipo Integer.
```

Donde el parametro `applicationDate` sirve para elegir la fecha por la que filtrar, el parametro `productId`
para escoger el producto a buscar (actualmente solo esta el producto con id: *35455*) y `brandId` para escoger la
marca por la que filtrar(en este caso solo funciona la marca *1*, que será ZARA).

Esta feature podrá ser invocada mediante el endpoint: `/prices-api/prices`,
dicho endpoint devolverá un objeto de tipo `PriceDTO` con los siguientes campos:

```
 Integer productId;
 String brandId;
 Integer priceList;
 LocalDateTime startDate;
 LocalDateTime endDate;
 Double price;
 ```

El campo `priceList` será el identificador de la tarifa aplicable.

Los campos `startDate` y `endDate` especificarán el rango de fechas en el que se aplica el precio de tarifa indicado.

Y el campo `price` sera el precio final de venta.

## Usos

Una llamada de prueba que se podría realizar con postman seria la siguiente:

``curl --location
'http://localhost:8080/prices-api/prices?applicationDate=2020-06-14T10%3A00%3A00&productId=35455&brandId=1'``

que devolvería el siguiente objeto:

```
{
    "productId": 35455,
    "brandId": "ZARA",
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.5
}
```

## Setup

### SE NECESITA HACER UN CLEAN INSTALL ANTES DE LEVANTAR LA APLICACIÓN.

Ya que la aplicación usa una base de datos H2, necesita los scripts `data.sql` y `schema.sql`.

El script `data.sql` se encarga de rellenar los datos de la bbdd cada vez que la aplicación se ejecute.

El script `schema.sql` crea la base de datos con su respectivo modelo.
En la raíz también esta el documento `DAMIANORDONEZP-pruebatecnicBCNC-1.0.0-swagger.yaml` donde podemos encontrar
la documento del endpoint y del modelo.

Para generar un reporte de JaCoCo ejecutar el siguiente comando de maven:  
`
mvn clean test
mvn jacoco:report
`  
Si se quiere ver el reporte generado por JaCoCo habría que acceder a la siguiente ruta:  
`target -> site -> jacoco -> index.html`

Levantar la aplicacion desde pricesapi y no desde la carpeta JavaMicroserviceBCNC.

## Contacto

Creado por [Dharian](https://github.com/daordonep/)

Cualquier feedback y critica constructiva es bien recibido.
También podeis contactar conmigo en [Linkedin](https://www.linkedin.com/in/damianordonezp/)  
o mandandome un correo a mi dirección de correo: damianordonezp@gmail.com
