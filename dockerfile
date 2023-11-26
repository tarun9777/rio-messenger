FROM openjdk:11 as builder

WORKDIR /app
COPY .mvn/ .mvn
COPY pom.xml mvnw ./
COPY ./src ./src
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package -DskipTests

FROM openjdk:11
WORKDIR /app
EXPOSE 8081
COPY --from=builder /app/target/*.jar /app/*.jar
ENTRYPOINT ["java","-jar","/app/*.jar"]
