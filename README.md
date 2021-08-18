# TAF
Test Automation Framework for 
Rest Endpoint https://swapi.dev/
and https://gorest.co.in/
use Java ver. 11 corretto
JUnit4, Allure 2.14.0

### Structure
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

### Execution
#### "mvn test" from root dir

### Reports
To be able to see the results in graphical view, you new to download allure framework from https://github.com/allure-framework
and add /bin to PATH in env variables (MS Windows)

To generate allure report go to output target dir and execute following command:
#### allure serve allure-results
