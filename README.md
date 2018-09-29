# corporate-game-share
App to register console games you own and share them with your friends and co-workers.

### API Documentation

The Swagger API documentation can be accessed via the following URL:

    http://<host>:<port>/swagger-ui.html

Local Development machine example:

    http://localhost:8080/swagger-ui.html

### Local Development Profile

These properties should be set in the `application-default.yml` file.

On a linux based system you can easily do this as follows:

    ./gradlew bootRun -i

When running from inside IntelliJ you can run or debug:

    CorporateGameSwapApplication.class

### React Build Process

You will need to install NPM by running `./gradlew npmInstall npmSetup`.
You can generate a new resource bundle by running `./gradlew npm_test`.
This bundle will be stored in `src/main/resources/static/built/bundle.js`.
To generate the production bundle, run `./gradlew npm_run_build`

Highly recommend you use the hot loading below to speed up development.

### Hot Reloading

#### React
Ensure that the `react-bundle` property is set to http://localhost:8081 then run `./gradlew npm_start`.
This will dynamically generate a new bundle everytime you make changes to any react files.
It is served on a different port and not the one that is bundled with Spring Boot.

#### Spring Boot
IntelliJ instructions:
- Go to Help -> Find Action
- Type "Registry".
- Find and mark : `compiler.automake.allow.when.app.running`.
- Close the Dialog.
- Go to Settings -> Build, Execution, Deployment -> Compiler.
- Mark "Build project automatically".
- Apply changes.


### H2 Console

Default/Local environment only:

    http://<host>:<port>/console/



