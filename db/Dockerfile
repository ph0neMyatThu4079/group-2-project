FROM mysql

WORKDIR /tmp

# Copy your SQL file into Docker container
COPY world.sql /docker-entrypoint-initdb.d/

# Set root password
ENV MYSQL_ROOT_PASSWORD toor