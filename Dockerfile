FROM openjdk:8-jdk-alpine
EXPOSE 9990
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]