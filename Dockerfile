FROM ewolff/docker-java
ADD target/gateway-0.0.1-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8080