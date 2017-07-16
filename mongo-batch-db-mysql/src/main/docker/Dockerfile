#
# 1. clean package docker:build
# 2. docker run --name db-mysql -e MYSQL_DATABASE=demo -e MYSQL_USER=username -e MYSQL_PASSWORD=password -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql:5.7
# 3. docker run -p 9001:9001 --name worker --link db-mysql:mysql -d mongo-batch-worker:latest
# 4. http://192.168.99.100:9001
# 5. docker exec -it db-mysql mysql -uroot -p [For Shell Access]
#
FROM oraclelinux:7-slim

# Install MySQL Server
RUN rpmkeys --import http://repo.mysql.com/RPM-GPG-KEY-mysql \
  && yum install -y https://repo.mysql.com/yum/mysql-5.7-community/docker/x86_64/mysql-community-server-minimal-5.7.18-1.el7.x86_64.rpm \
  && yum install -y libpwquality \
  && rm -rf /var/cache/yum/*
RUN mkdir /docker-entrypoint-initdb.d

VOLUME /var/lib/mysql

COPY docker-entrypoint.sh /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

EXPOSE 3306 33060

CMD ["mysqld"]