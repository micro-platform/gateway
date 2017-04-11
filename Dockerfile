FROM ewolff/docker-java

ARG version

ENV version $version

ADD gateway-$version.jar .

EXPOSE 8080

CMD /usr/bin/java -Xmx400m -Xms400m -jar gateway-$version.jar
