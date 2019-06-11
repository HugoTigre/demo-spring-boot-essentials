# Spring Boot Essentials Demo

This is a Spring Boot project with an implementation of "essentials" that can be useful in any project.

## List of Essentials

- __Kotlin__ codebase

- __Clean Architecture__ implementation

  - Includes Core, Delivery and Data layers. Data layers uses Postgresql, so this needs to be configured first, see `application.yml`
  - If Clean Architecture is unknown for you, you should first read about it (https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164).

- __Flyway__: for database evolutions

- Rest endpoints (GET, POST, PUT, DELETE)

- Request and Response logging filter

- Logging with Logback

  - There is a `logback.xml` and `logback-prod.xml`, the latter differs in that instead of logging to STDOUT, it logs to File and can report Errors by mail.
  
- __Code First API documentation__ with SWAGGER (OpenAPI)

  - Controllers implement a controller interface to separate documentation dependency
   from controllers implementation, also, this abstracts REST dependency in case there is
   a need to replace it with another protocol
  - documentation can be access in url http://localhost:8080/swagger-ui.html

- __Feature Flags__: with 

- Tests:
  
  - Unit and Integration tests
  
  - __Test Containers__: Integration tests with docker containers implementation to standardize testing environment across developers and environments (www.testcontainers.org)
    - the demo uses a postgresql container, but this could easily be replace by another kind of container, the logic is basically the same with other containers
    
  - __Jacoco__: Code coverage (https://github.com/jacoco/jacoco)
  
        gradle jacocoFullReport
            
  - __Load Testing: with Gatling__ (this should be in a separate project with load distribution capabilities, but this serves as an example) (https://gatling.io)
    - build settings in separate file (build-gatling.gradle)

          gradle testLoad
          
Notes: 
- The implementations shown here, despite following best practices, are to be viewed as suggestive 
and not as the only way to do the same thing.
- All the source code is in kotlin with exception of tests. Test containers are in Java, load tests 
in scala and all other tests are in Kotlin. Test container implementation could be easily refactor 
to Kotlin, but Gatling uses an API in scala, so it's advisable to use scala for this, however, note 
that this is a really simple usage of the language. As said before, the load testing should be in 
a separate project.

## Build, Run and Test

    gradle clean build
     
    gradle bootRun -Dspring.profiles.active=default # Change profile acording to environment (local, dev, qa, prod, etc)
    
    gradle jacocoFullReport # Code coverage
    
    gradle testLoad # Load testing
        
----
Hugo Tigre (hugo.tigre@gmail.com)

