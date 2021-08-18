# TAF
Test Automation Framework for 
Rest Endpoint https://swapi.dev/
and https://gorest.co.in/
use Java ver. 11 corretto
use of 3-party libraries
- JUnit4
- Allure 2.14.0
- Rest Assured 4.0.0
- jackson-annotations 2.11.3
- json-path 2.4.0
- org.slf4j 1.7.30 
- commons-beanutils 1.9.3
- json-schema-validator 1.0.42

## Structure
+ com.tests
    + ComponentTests
        + GoRestApiCRUDTests.java
        + SWApiHeadersTests.java
        + SWApiNegativeScenariosTests.java
        + SWApiSearchTests.java
    + ContractTests
        + SWApiFilmsSchemaTests.java
        + SWApiPeopleSchemaTests.java
        + SWApiPlanetsSchemaTests.java
        + SWApiSpeciesSchemaTests.java
        + SWApiStarshipsSchemaTests.java
        + SWApiVehiclesSchemaTests.java
    + HealthTests
        + SWApiAssertFalseHealthTests.java
        + SWApiHealthTest.java

## Execution
#### "mvn test" from root dir

## Reports
To be able to see the results in graphical view, you new to download allure framework from https://github.com/allure-framework
and add /bin to PATH in env variables (MS Windows)

To generate allure report go to output target dir and execute following command:
#### "allure serve allure-results"
