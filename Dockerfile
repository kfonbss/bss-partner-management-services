FROM amazoncorretto:17.0.8-al2023-headless AS build

ARG WORK_DIR
ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

WORKDIR /app

COPY ${WORK_DIR}/pom.xml ./pom.xml
COPY ${WORK_DIR}/start.sh ./start.sh
COPY ${WORK_DIR}/.mvn ./.mvn
COPY ${WORK_DIR}/mvnw ./mvnw
COPY ${WORK_DIR}/src ./src
COPY ${WORK_DIR}/version.properties ./version.properties

RUN mkdir -p /root/.m2 && \
    echo "<settings><servers><server><id>github</id><username>${GITHUB_USERNAME}</username><password>${GITHUB_TOKEN}</password></server></servers></settings>" > /root/.m2/settings.xml

RUN /app/mvnw -B -f /app/pom.xml package -DskipTests

FROM amazoncorretto:17.0.8-al2023-headless
WORKDIR /opt/egov

COPY --from=build /app/target/*.jar /app/start.sh /opt/egov/

RUN chmod +x /opt/egov/start.sh

CMD ["/opt/egov/start.sh"]
