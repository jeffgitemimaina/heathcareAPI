FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . /app
RUN ./gradlew build -x test
EXPOSE 9000
CMD ["java", "-jar", "build/libs/healthcare-api-backend-0.0.1-SNAPSHOT.jar"]