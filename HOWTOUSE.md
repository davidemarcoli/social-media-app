# How to use this project template

This project template is made with the intention of making it easy to start a new project.
The Template includes the following features:

- A multi-module project structure with a parent pom and 2 submodules (`frontend` and `backend`)
- Backend
  - Spring Boot
  - Spring Data JPA (with Hibernate)
  - Spring Security (JWT)
  - Spring Web (REST)
- Frontend
  - Angular 14
  - Routing
  - Bootstrap 5
  - Font Awesome 5

There are a few things you need to do before you can start developing your project.

- [ ] Change the groupId and artifactId in the `pom.xml` files
  - [ ] `frontend/pom.xml`
  - [ ] `backend/pom.xml`
  - [ ] `pom.xml`
  - [ ] Reload the maven project by right-clicking on the `pom.xml` file and selecting `Reload Maven Project`
- [ ] Change the project name in the `package.json` file
- [ ] Change the project name in the `angular.json` file
  - [ ] `frontend/angular.json` (line 6)
  - [ ] `frontend/angular.json` (line 16)
  - [ ] `frontend/angular.json` (line 68)
  - [ ] `frontend/angular.json` (line 71)
  - [ ] `frontend/angular.json` (line 79)
- [ ] Change the project name in the navbar in the `app.component.html` file
  - [ ] `frontend/src/app/app.component.html` (line 2)
- [ ] Change the configuration in the `application.properties` file located at `backend/src/main/resources`
- [ ] Change the docker image name in the `buildDockerfile.sh` file located at `scripts`
- [ ] Run `mvn clean install` in the root directory of the project to verify that everything works

**Now you're Ready!**

## Backend

### Database

The backend uses a MySQL database. You can either use a local database or a remote database.

#### Local Database
If you want to use a local database, you need to install MySQL on your machine.

With docker, you can easily start a MySQL database in seconds. Just run the following command:

```bash
docker run --name project-template-mysql -e MYSQL_ROOT_PASSWORD=my-password -e MYSQL_DATABASE=my-database -p 3308:3306 -d mysql:latest
```

If you don't have docker installed, you can download it [here](https://www.docker.com/products/docker-desktop).

When you don't want to use docker, you can download MySQL [here](https://dev.mysql.com/downloads/mysql/).

#### Remote Database

If you want to use a remote database, you need to create a MySQL database on a remote server.

### Configuration

The backend uses Spring Boot. The configuration is done in the `application.properties` file.
You can find this file in the `backend/src/main/resources` folder.

The following properties are used:

- `spring.datasource.url`: The URL of the database. If you use a local database, the URL is `jdbc:mysql://localhost:3306/<database_name>`. If you use a remote database, the URL is `jdbc:mysql://<server_ip>:3306/<database_name>`.
- `spring.datasource.username`: The username of the database user.
- `spring.datasource.password`: The password of the database user.
- `spring.jpa.hibernate.ddl-auto`: The database schema update strategy. The default value is `update`. This means that the database schema is updated when the application is started. If you want to disable this, you can set the value to `none`.
- `spring.jpa.properties.hibernate.dialect`: The database dialect. The default value is `org.hibernate.dialect.MySQL5InnoDBDialect`. If you use a different database, you need to change this value.
- `spring.jpa.show-sql`: If you want to see the SQL queries that are executed by Hibernate, you can set this value to `true`.
- `spring.jpa.properties.hibernate.format_sql`: If you want to see the SQL queries that are executed by Hibernate in a formatted way, you can set this value to `true`.

### JWT

The backend uses JWT for authentication. The JWT secret is stored in the `application.properties` file.
You can find this file in the `backend/src/main/resources` folder.

The following properties are used:

- `jwt.secret`: The JWT secret. You can generate a random secret with the following command: `openssl rand -base64 32`.
- `jwt.expiration`: The JWT expiration time in milliseconds. The default value is `86400000` (24 hours).
- `jwt.header`: The JWT header. The default value is `Authorization`.
- `jwt.prefix`: The JWT prefix. The default value is `Bearer `.

### Run

You can run the backend with the following command:

On Windows:

```
./mvnw spring-boot:run
```

On Linux:

```
mvn spring-boot:run
```

### Build

You can build the backend with the following command:
On Windows:

```
./mvnw clean package
```

On Linux:

```
mvn clean package
```

## Frontend

### Configuration

The frontend uses Angular. The configuration is done in the `environment.ts` file.
You can find this file in the `frontend/src/environments` folder.

There are 2 environment files:
- `environment.ts`: The development environment.
- `environment.prod.ts`: The production environment.

The following properties are used:

- `apiUrl`: The URL of the api.

### Run

You can run the frontend with the following command:

```
ng serve
```

### Build

You can build the frontend with the following command:

```
ng build
```

### Included Libraries
- [Angular](https://angular.io/)
- [Angular Material](https://material.angular.io/)
- [CKEditor 5](https://ckeditor.com/ckeditor-5/)
- [JWT Decode](https://www.npmjs.com/package/jwt-decode)
- [Moment](https://momentjs.com/)
- [ngx-pipes](https://www.npmjs.com/package/ngx-pipes)
- [rxjs](https://rxjs.dev/)

## Build Whole Project

### Local

You can build the whole project with the following command in the root folder:

On Windows:

```
./mvnw clean package
```

On Linux:

```
mvn clean package
```

### Docker

You can build a Docker image of the backend with the following command:

```
docker build -t <image_name> .
```

You can run the Docker image with the following command:

```
docker run -p 8080:8080 <image_name>
```
