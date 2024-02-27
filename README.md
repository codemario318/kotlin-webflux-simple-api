# REST API: Kotlin + Spring WebFlux + PostgreSQL

[Spring Initializr](https://start.spring.io/#!type=gradle-project-kotlin&language=kotlin&platformVersion=3.2.3&packaging=jar&jvmVersion=17&groupId=com.example&artifactId=webflux&name=webflux&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.webflux&dependencies=webflux,data-r2dbc,postgresql)

## DB Setup
 
```shell
docker pull postgres
docker run --name app-postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -d postgres
``` 
---
```shell
sudo docker exec --user="root" -it app-postgres "bash"
psql -U postgres
```
---
```sql
CREATE TABLE app_user(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO app_user 
VALUES ('1', 'user1', 'user1@gmail.com'),
    ('2', 'user2', 'user2@gmail.com')
;
```

