FROM openjdk:11
ADD target/genderDetector.jar genderDetector.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","genderDetector.jar"]