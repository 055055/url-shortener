FROM eclipse-temurin:11 as base

WORKDIR /app

COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle

# build
FROM base AS build
WORKDIR /app

COPY ./url-shortener ./url-shortener
COPY ./common ./common

RUN ./gradlew clean
RUN ./gradlew --no-daemon build -x test

# release
FROM eclipse-temurin:11
WORKDIR /app

COPY --from=build /app/**/build/libs/url-shortener-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","./app.jar"]
