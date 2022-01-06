Plant API

Below are a few sample urls for testing

Get plants  by state
http://localhost:8080/test/plants/state/AK?sort=generatorAnnualNetGeneration,desc

Get plant by id
http://localhost:8080/test/plants/10

get top plants by generation output
http://localhost:8080/test/plants/top/?orderBy=desc&size=10

get bottom plants by generation output
http://localhost:8080/test/plants/bottom/?orderBy=desc&size=10

Swagger UI
http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs
