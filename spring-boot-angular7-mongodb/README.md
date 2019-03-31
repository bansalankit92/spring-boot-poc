
# Spring boot, Angular7, MongoDb integration  
You can download the repo rename/refactor the classes and pom and directly start the implementation,

### Features Implemented in backend
 - Application wide Error handling,
 - AOP - services and controller traces and performance logger, 
 - MVC architecture implementation
 - On every maven build the UI gets build and stored in resourses/dist folder
 - UI runs on same backend port on http://localhost:8080/
 - Cors implementation
 - Message source for i18n
 - Swagger-ui on http://localhost:8080/swagger-ui.html which doesn't works on prod env
 - Mail service
 
### Features Implemented in frontend

 - Routing 
 - HttpClient service
 - Material ui
 - Build output is stored in backend resourses/dist folder
 - User guard

## Steps to Run
Create `mongodb` database: `spring-angular` 
    
    mvn clean install
    java -jar target/SpringBootAngular7Mongodb-0.0.1-SNAPSHOT.jar
Open browser: http://localhost:8080
Swagger url : http://localhost:8080/swagger-ui.html
### To run backend as Dev
Just do maven clean install and run the java app
### To run frontend as Dev
make sure your backend service is running
go to 
`cd ng-poc-ui` 
`npm install`
`ng serve`
and open browser on port 4200 - http://localhost:4200
in `environment.ts` file paste your backend service url
default is `REST_API_LOCATION: 'http://localhost:8080'`

Thats it I guess, your backend Java spring boot project and Angular7 project is up and running and you can start your implementation.

For any Java, Angular related poc or sample application mail me at bansal.ankit92.ab@gmail.com