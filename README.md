**Service** 

Add this to run the proyect locally
```properties
--spring.profiles.active=local
```
docker run --name stock-market-bd -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres