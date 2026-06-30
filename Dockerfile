# ==========================================
# Stage 1 - Build
# ==========================================

FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw

#RUN ./mvnw dependency:go-offline

COPY . .

RUN ./mvnw clean package -DskipTests

# ==========================================
# Stage 2 - Runtime
# ==========================================

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]