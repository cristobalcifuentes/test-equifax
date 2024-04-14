-v1

- se completa la primera parte de la prueba tecnica equifax
- en esta primera parte se crea un mvp con la funcionalidad base de leer un archivo excel, guardar su informacion en base de datos y responder con la informacion guardada en formato json
- para levantar el proyecto y conectar correctamente con una base de datos local se deberan cambiar 3 configuraciones en el archivo ubicado en la ruta: src/main/resources/application.properties
  - spring.datasource.url:jdbc:postgresql:url_de_conexion
  - spring.datasource.username=username_de_conexion_a_bbdd
  - spring.datasource.password=password_de_conexion_a_bbdd
- no se necesita script de creacion de base de datos dado que spring esta configurado para crear automaticamente las tablas una vez se levanta el proyecto y se conecta a bbdd.
- para ver la funcionalidad del test se debe conectar atravez de un cliente de servicios web como postman a la url "http://localhost:1010/file" mediante un metodo post con un archivo excel (en la raiz del proyecto tenemos un ejemplo) como parametro con el nombre de "file", como respuesta se obtendra lo guardado en bbdd


v2
-se agrega funcionalidad para crear y registrar nuevos usuarios encriptando la contraseña, loguearse en la aplicacion mediante la obtencion de un jwt unico y para lograr procesar el archivo se debe incluir el jwt valido y vigente, de lo contrario se recibira un error 403 como fue solicitado.
- se agrega coleccion postman con la que se puede revisar las funcionalidades del test 
