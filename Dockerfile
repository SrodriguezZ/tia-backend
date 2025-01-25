FROM eclipse-temurin:17.0.12_7-jdk

EXPOSE 80

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

RUN chmod +x /root/mvnw

RUN ./mvnw dependency:go-offline

COPY ./src /root/src

RUN ./mvnw clean install -DskipTests

COPY ./target/proyecto-global-0.0.1-SNAPSHOT.jar /root/target/proyecto-global-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/root/target/proyecto-global-0.0.1-SNAPSHOT.jar"]