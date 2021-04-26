FROM openjdk:11.0.11-jre
MAINTAINER Breno Santos e Alves <bsantosealves@gmail.com>

ENV SERVER_PORT="8080" \
	CONTEXT="/api/v1" \
	DIALECT="org.hibernate.dialect.MySQLDialect" \
	HIBERNATE_DRIVER_CLASS_NAME="com.mysql.cj.jdbc.Driver" \
	DB_USER="root" \
	DB_USER_PASSWORD="root" \
	DB_URL="jdbc:mysql://localhost:3306/desafio_stoom" \
	GOOGLE_MAPS_API_KEY="AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw"

EXPOSE $SERVER_PORT

COPY ./build/libs/desafio-stoom-1.0-SNAPSHOT.jar /app/desafio-stoom.jar

WORKDIR "/app"

ENTRYPOINT ["java", "-jar", "desafio-stoom.jar"]
