FROM openjdk:11
EXPOSE 8089
ADD /target/DevOpsProject.jar DevOpsProject.jar
ENTRYPOINT ["java", "-jar", "/DevOpsProject.jar"]