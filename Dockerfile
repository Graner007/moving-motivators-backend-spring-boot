FROM adoptopenjdk/maven-openjdk11 as build

WORKDIR /app

COPY src src
COPY pom.xml .

RUN mvn -q -f pom.xml clean install -DskipTests=true


FROM openjdk:14-alpine

WORKDIR /app

COPY --from=build app/target/*.jar app.jar

COPY src/main/resources/media src/main/resources/media

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
