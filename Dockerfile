FROM openjdk:21-jdk-slim
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x gradlew
RUN ./gradlew bootJar -x test
EXPOSE 9000
CMD ["java", "-jar", "build/libs/healthcare-api-backend-0.0.1-SNAPSHOT.jar"]