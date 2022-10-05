FROM openjdk:11
COPY target/filepractice-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","filepractice-0.0.1-SNAPSHOT.jar"]