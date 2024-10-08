**Application Startup**
* Step1: Start docker engine
* Step2: run `docker compose up mysql postgres vault -d`
* Step3: run `mvn clean install -DskipTests`
* Step4: run `mvn spring-boot:run` or start the application from the IDE

**Database connection**

* user: `hydrogenhr`
* password: `password@123`

**API Documentation**
* Swagger: http://localhost:8099/api/v1/swagger-ui.html

***Login is via Postman:***
* Step1: Open postman
* Step2: Go to Authorization and select Oauth 2.0
* Step3: Go to Configure new Token
* Step4: Enter the following details:
    * Token Name: `HydrogenHR`
    * Grant Type: `Authorization Code (With PKCE)`
    * Callback URL: `https://getpostman.com/oauth2/callback`
    * Auth URL: `http://localhost:8099/api/v1/oauth2/authorizee`
    * Access Token URL: `http://localhost:8099/api/v1/oauth2/token`
    * Client ID: `foundation-service`
    * Client Secret: `welcome`
    * Scope: `openid profile email` Note that any of the scopes can be used
    * Client Authentication: `Send client credentials in body`
* Step5: Click on Get New Access Token
* A login page will be displayed, enter the following details:
    * Username: `superadmin`
    * Password: `password@123`
* Click on Login
* Click on Use Token
