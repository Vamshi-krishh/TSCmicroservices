<<<<<<< HEAD
FROM openjdk:20

WORKDIR /app

COPY target/microservice-0.0.1-SNAPSHOT.jar /app/tv.jar

EXPOSE 8083

CMD ["java", "-jar", "tv.jar"]











=======
FROM openjdk:20

WORKDIR /app

COPY target/microservice-0.0.1-SNAPSHOT.jar /app/tv.jar

EXPOSE 8083

CMD ["java", "-jar", "tv.jar"]











>>>>>>> 1fb768f9b744b845561c4381bdfda96a3c100935
