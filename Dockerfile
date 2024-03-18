FROM amazoncorretto:17-alpine3.18-full
LABEL maintainer="Edwin Munoz <edwinjmunoz@gmail.com>"
LABEL description="API CRUD Operations"

ENV EMAIL_PATTER=^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$
ENV PASSWD_PATTER=^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$
ENV JWT_KEY=T2stCr4dAPi20241234QuickTechTest123475sdfrew6422421234EMR32213La4raP14l1C1m3l20032443

COPY build/libs/crud-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar -Duser.timezone=$TIMEZONE -Dnetworkaddress.cache.ttl=60 -Dnetworkaddress.cache.negative.ttl=30 app.jar" ]
