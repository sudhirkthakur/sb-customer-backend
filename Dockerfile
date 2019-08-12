FROM adoptopenjdk/openjdk8:alpine-slim
WORKDIR /opt/app
COPY target/customer-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENV JAVA_OPTIONS ''
CMD java $JAVA_OPTIONS -jar customer-0.0.1-SNAPSHOT.jar
