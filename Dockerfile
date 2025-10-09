# Multi-stage build for optimized image size
FROM maven:3.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built artifact from build stage
COPY --from=build /app/target/Trip-MateV2-*.jar app.jar

# Expose the application port
EXPOSE 9090

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:9090/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
