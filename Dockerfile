# Etapa de build
FROM openjdk:17-alpine AS build
WORKDIR /app

# Copiamos gradle wrapper y configuraciones
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle.kts settings.gradle.kts ./

# Copiamos el código fuente
COPY src ./src

# Damos permisos de ejecución al gradlew
RUN chmod +x gradlew

# Compilamos el proyecto (sin tests)
RUN ./gradlew build -x test

# Etapa final (imagen liviana)
FROM openjdk:17-alpine
WORKDIR /app

# Copiamos el jar generado desde la etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Exponemos el puerto del backend
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]