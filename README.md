# Fuber
Fuber Cab (Spring MVC, Hibernate, Maven Project)

# Requirements
Mysql, Maven, Tomcat

# Steps to Execute
1. Create fuber_db database in your mysql
2. Edit as per your configuration /src/main/resources/hibernate.properties & log4j.properties
3. Now in the root directory execute 
    mvn install
4. Deploy target/Fuber.war

# API's

Base URL: localhost:8080/Fuber

1. Create Taxis: Request-Type = Post

    API: /taxi/create  
    
    Sample Payload: { "registrationNumber": "rj14VD4148", "driverName": "KKG", "mobileNumber": 9460413922, "email": "kkg2@gmail.com", "taxiType": { "id": 1 }, "lat": 12.23, "lon": 32.24, "occupied": false }

2. Create a User: Request-Type = Post

    API: /user/create

    Sample Payload: { "firstName": "kushal", "lastName": "goyal", "email": "kkg@gmail.com", "mobileNumber": 9460413921 }

3. Book a Taxi: Request-Type = Post

    API: /booking/bookTaxi

    Request-Params: "userId=2&userLat=1&userLon=1&taxiType=SEDAN"

4. Start the Ride

    API: /booking/startRide

    Request-Params: "bookingId=5"

5. End the Ride

    API: /booking/endRide

    Request-Params: "bookingId=5&endLat=8&endLon=8"
