FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

COPY ./pom.xml .
COPY ./.mvn ./.mvn
COPY ./mvnw .

RUN ./mvnw clean package -Dmaven.test.-Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/employee-management-api-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "employee-management-api-0.0.1-SNAPSHOT.jar"]