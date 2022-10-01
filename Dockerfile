FROM openjdk:11
EXPOSE 8081
ADD target/mybookmark.jar mybookmark.jar
ENTRYPOINT [ "java","-jar","/mybookmark.jar" ]

