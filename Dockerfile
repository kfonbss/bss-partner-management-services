# syntax=docker/dockerfile:1

# =========================
# 🏗️ Build Stage
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only pom.xml first (to leverage Docker caching)
COPY pom.xml ./

# Pre-download dependencies (optional but faster for later rebuilds)
RUN mvn -B dependency:go-offline -Dmaven.test.skip=true

# Copy source code (this invalidates cache only if code changes)
COPY src ./src
COPY start.sh .
COPY version.properties .

# Build the JAR (no need to run dependency:go-offline again)
RUN mvn -B clean package -DskipTests

# =========================
# 🚀 Runtime Stage
# =========================
FROM eclipse-temurin:21-jre-jammy
WORKDIR /opt/egov

# Copy JAR & start script
COPY --from=build /app/target/*.jar .
COPY --from=build /app/start.sh .

# Fix line endings and permissions
RUN sed -i 's/\r$//' start.sh && chmod +x start.sh

# Run as non-root user
RUN useradd -m appuser
USER appuser

EXPOSE 8080
CMD ["./start.sh"]