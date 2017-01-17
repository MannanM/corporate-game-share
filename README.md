# corporate-game-share
App to register console games you own and share them with your co-workers.

### API Documentation

The Swagger API documentation can be accessed via the following URL:

    http://<host>:<port>/swagger-ui.html

Local Development machine example:

    http://localhost:8080/swagger-ui.html

### Local Development Profile

Set the active profile to "local" to pick handy development defaults.

These properties should be set in the **application-local.properties** file.

On a linux based system you can easily do this as follows:

    SPRING_ACTIVE_PROFILES=local ./gradlew bootRun -i

When running from inside IntelliJ you can set the "VM Options" on the run configuration to:

    -Dspring.profiles.active=local


**H2 console:**

    http://<host>:<port>/console/



