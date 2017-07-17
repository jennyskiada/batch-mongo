#
# 1. docker run --name logging --volume /applicationLogs alpine:latest
# 2. clean package docker:build
# 3. docker run -p 9001:9001 --name worker --volumes-from logging --link db-mysql:mysql -d mongo-batch-worker:latest
# 4. http://192.168.99.100:9001 [Web Interface]
# 5. docker exec -it worker /bin/sh [For Shell Access]
#
FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 9000

# Install Spring Boot Artifact
VOLUME /tmp
ADD mongo-batch-worker-1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]