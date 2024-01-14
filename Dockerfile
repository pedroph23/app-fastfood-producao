FROM amazoncorretto:17-alpine-jdk

WORKDIR /code
COPY ./app/target /code/app

CMD ["sh", "-c", "java -jar /code/app/app-fastfood-produto-0.0.1.jar"]