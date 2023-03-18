# CGM Technical Test

Web app for cgm technical test

## Description

The solution:
- basic frontend angular project with angular materia;
- parent backend project called cgm-test with two submodules: patient-service and visit-service
- two backend quarkus project: patient-service and visit-service
- one mysql db for patient-service and one mysqò db for visit-service

## Installazione ed esecuzione

To deploy the application, after cloning the project, run `docker-compose up` to bring up the Angular GUI and the two MySQL databases as containers. SQL scripts will be executed to create the tables and populate them with some initial example data.

Then you need to start the two services, patient-service and visit-service (I couldn't dockerize these Quarkus applications, let's discuss how to do it please!!!).

The GUI will be reachable at this address: `http://localhost`, while the endpoints of the two services will be: `localhost:8100/patient` and `localhost:8080/visit`.

## Enhancements

- Dockerize the two quarkus projects
- Add authorization and add SSL
- Better dockerize the two mysql dbs for a production deploy
- Improve the frontend project, there is no error handling for example, it's basic for time reason
- Add all the CRUD operation, I have done just the requested for the workflow for time reason
