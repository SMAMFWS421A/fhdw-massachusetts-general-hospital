FROM maven:3-amazoncorretto-17

WORKDIR /app
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run
