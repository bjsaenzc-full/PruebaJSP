# ================================ #
# == INSTRUCCIONES DE EJECUCIÓN == #
# ================================ #
 
A. Requerimientos:
    1. Apache Tomcat 9.x
    2. JDBC Driver para MySQL(Conector/J): "mysql-connector-java-8.0.22.jar", el cual esta disponible en:
        --> Dentro del directorio --> cotizacioncredito/WEB-INF/classes/lib/mysql-connector-java-8.0.22.jar
        --> En la siguiente dirección URL: "https://dev.mysql.com/downloads/connector/j/"
  
B. Instrucciones de uso:

Para el correcto funcionamiento, se debe tener el jdbc-driver dentro del directorio lib/ de Tomcat.

B.1. Creación y llenado de la base de datos
    B.1.1.  Se asume que se cuenta con una base de datos en MySQL, para este caso denominada "socios", y una tabla llamada "datos",
            con almenos las siguientes columnas (o sus equivalentes) "nombre, tasaDeInteres, montoMaxDisp".

            En caso de que el nombre de alguna de estas características (base, tabla, columnas) difiera de los acá presentados, se debe
            modificar la variable "sql_query" del método leerSocios(), de la clase LeerSocio.java

            No obstante, dentro de directorio "cotizacioncredito/WEB-INF/classes/cotizacion/model/" se encuentran los archvos *.sql 
            respectivos para creación de la base de datos en MySQL:
                --> sociosDB.sql (creación de la base de datos)
                --> llenar_socios.sql (con los ejemplos del archivo "Prueba.docx")
                --> llenar_socio1.sql (con 1000 entradas, el cual fue generado con ayuda de Python-Pandas)
            Así, que solo para la creación de la base de datos, solo sería necesario ejecutar desde MySQL los archivos *.sql mencionados antes

B.2. Datos de conexión a MySQL
    B.2.1.  En el archivo descriptor de implementación (Deployment descriptor) "web.xml" modificar los parámetros de acceso a la base de 
            datos MySQL. Especificamente:
                --> NombreUsuario <--> debe modificarse por el nombre de usuario con acceso a la base de datos MySQL
                --> PassUsuario <--> similarmente, debe modificase de manera acorde 

B.3. En cuanto a la ejecución. Una vez realizados los cambios anteriores en "web.xml" (y en sql_query si es necesario) se debe:
    B.3.1. Copiar el directorio completo "cotizacioncredito/" dentro del directorio webapps/ de Tomcat
    B.3.2. Iniciar el servidor Tomcat
    B.3.3. En el explorador acceder desde el localhost, puerto 8080 a la ruta /cotizacioncredito/inicio, es decir:
            "localhost:8080/cotizacioncredito/inicio"

