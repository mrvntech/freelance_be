FROM openjdk
FROM maven
ARG MY_VAR
ENV MY_VAR=${MY_VAR}
WORKDIR /freelance_be
COPY . .
RUN #mvn clean install && java -D${MY_VAR} -jar target/freelance_be.jar
RUN mvn clean install
#ENTRYPOINT ["bash"]
ENTRYPOINT ["java", "-jar", "target/freelance_be.jar"]