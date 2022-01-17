# polytech-blog
Web blog application written on Kotlin with [Ktor framework](https://ktor.io/)<br>
- [Docker](https://www.docker.com/) was used to create docker image<br>
- [Docker Compose](https://www.docker.com/docker-compose) was used to create container with MySQL, [liquibase](https://liquibase.org/) migrations and my application

## Usage
> **_NOTE:_**
gradle is shortcut for gradlew or gradlew.bat depending on your operating system

1. `gradle installDist` - assemble the uncompressed distribution with gradle
2. `docker compose up` - run docker container
