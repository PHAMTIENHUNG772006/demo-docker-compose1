# Stage 1: Build ứng dụng
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Cài đặt các công cụ cần thiết để tải và giải nén Gradle
RUN apt-get update && apt-get install -y wget unzip && rm -rf /var/lib/apt/lists/*

# Tải và cài đặt Gradle 9.4.1-bin.zip
ENV GRADLE_VERSION=9.4.1
RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip \
    && mv gradle-${GRADLE_VERSION} /opt/gradle \
    && rm gradle-${GRADLE_VERSION}-bin.zip

# Đưa Gradle vào biến môi trường PATH
ENV PATH="/opt/gradle/bin:$PATH"

# Copy các file cấu hình build vào container trước để tận dụng Docker layer caching
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Tải dependencies trước (giúp build nhanh hơn ở các lần sau nếu code thay đổi)
RUN gradle dependencies --no-daemon

# Copy toàn bộ mã nguồn còn lại
COPY src ./src

# Build ra file JAR thực thi
RUN gradle bootJar --no-daemon

# Stage 2: Chạy ứng dụng (Runtime image tối ưu, nhẹ hơn)
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy file JAR đã được build từ stage builder sang
COPY --from=builder /app/build/libs/*.jar app.jar

# Mở cổng port mặc định của Spring Boot (thường là 8080)
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]