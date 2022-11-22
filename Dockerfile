FROM openjdk:8
EXPOSE 8089
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ONBUILD ADD . /usr/src/app
ONBUILD RUN mvn install
ONBUILD ADD /usr/src/app/target/achat-1.0.jar app.jar

CMD ["java","-jar","/app.jar"]