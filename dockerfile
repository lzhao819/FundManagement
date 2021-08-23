version: '2.2'
services:
  mysql:
    container_name: mysql
    build:
      context: .
      dockerfile: Dockerfile-mysql
    image: fundmngmt/mysql:1.0.0
    ports:
      - "3306:3306"
    volumes:
      - /docker/fundmngmt/mysql:/var/lib/mysql
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD:
    command: --explicit_defaults_for_timestamp

  app:
    container_name: app
    build:
      context: .
      dockerfile: Dockerfile-app
    image: fundmngmt/app:1.0.0
    ports:
      - "8080:8080"
    links:
      - mysql:mysql
    depends_on:
      - mysql