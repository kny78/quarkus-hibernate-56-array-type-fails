# quarkus-hibernate-56-array-type-fails

This project illustrates that Hibernate-types fails for Quarkus 2.2.1.Final, but not Quarkus 2.1.1.Final

## Runs ok with 2.1.1.Final

Start the container with 2.1.1
```
./mvnw clean  quarkus:dev -Dquarkus.platform.version=2.1.1.Final
```

Test the instance with
```
curl http://localhost:8080/hello
```


## Fails with 2.2.1.Final

Start the container with 2.2.1
```
./mvnw clean  quarkus:dev -Dquarkus.platform.version=2.2.1.Final
```

Test the instance with
```
curl http://localhost:8080/hello
```


## Tests run ok with both
```
./mvnw clean install -Dquarkus.platform.version=2.1.1.Final
./mvnw clean install -Dquarkus.platform.version=2.2.1.Final
```