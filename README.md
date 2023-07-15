<p align="center">
  <a href="https://spring.io/projects/spring-boot" target="blank"><img src="https://frontbackend.com/storage/tutorials/thymeleaf/spring-boot-logo.png" width="130" alt="Nest Logo" /></a>
   <a href="https://www.docker.com/" target="blank"><img src="https://storage.googleapis.com/static.ianlewis.org/prod/img/docker/large_v-trans.png" width="150" alt="Nest Logo" /></a>
</p>

# Levantar la base de datos de Postgres con docker

1) clone el repositorio


```bash
 https://github.com/velfin13/prueba_tecnica.git

  cd prueba_tecnica/db
```
    
2) Configure sus credenciales de la base de datos en el archivo **.env**
```
POSTGRES_USER=velkin
POSTGRES_PASSWORD=password
POSTGRES_DB=prueba_tec
```

3) Levante el servicio con
```bash
  docker-compose up -d
```

4) Para bajar el servicio
```bash
  docker-compose down
```
## En el cliente de la base de datos ejecute el procedimiento almacenado para eliminar intentos de inicio de session

```
CREATE OR REPLACE PROCEDURE reset_intentos_sesion()
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE users SET intentos_sesion = 0 WHERE intentos_sesion > 0;
    COMMIT;
END;
$$;
```


# Para levantar la Api en modo desarrolo

1) ubicarse en la raiz del proyecto y ejecute


```bash
  ./mvnw spring-boot:run
```
    


## Para ver los endpints escriba en el navegador 
```
https://documenter.getpostman.com/view/14749617/2s946bCFC9
```

