FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Копируем pom.xml и загружаем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN mvn clean package -DskipTests

# Финальный образ
FROM eclipse-temurin:21-jre

WORKDIR /app

# Копируем собранный JAR
COPY --from=build /app/target/*.jar app.jar

# Ожидаем готовности БД и запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]