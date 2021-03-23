FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S textpostjava && adduser -S textpostjava -G textpostjava
USER textpostjava:textpostjava
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]