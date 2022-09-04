# Find Alticci Sequence Number

Pass in an alticci index and retrieve its sequence number.

## Clone code:

`git clone git@github.com:julio-e-ruiz-negron/alticci.git`

## Run app in local dev machine:

`cd alticci`

`./mvnw spring-boot:run`

App should be running in local dev machine at: [http://localhost:5000](http://localhost:5000)

Local dev machine access to Swagger UI (Open API): [http://localhost:5000/swagger-ui/index.html](http://localhost:5000/swagger-ui/index.html)

## Build App and Run it inside Docker:

`./mvnw clean install`

`docker build -t altice/alticci-sequence-finder .`

`docker run -d -p:5000:5000 altice/alticci-sequence-finder`

App is currently running and hosted in AWS (Elastic Beanstalk): [https://alticci.jeruiz-negron.com/](https://alticci.jeruiz-negron.com/)

Swagger UI (Open API) can be accessed at: [https://alticci.jeruiz-negron.com/swagger-ui/index.html](https://alticci.jeruiz-negron.com/swagger-ui/index.html)
