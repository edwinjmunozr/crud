FROM amazoncorretto:17-alpine3.18-full
LABEL maintainer="Edwin Munoz <edwinjmunoz@gmail.com>"
LABEL description="CRUD Operations"
COPY build/libs/crud-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar -Duser.timezone=$TIMEZONE -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=30 app.jar" ]
