# 주문시스템으로 알아보는 분산트랜잭션
## ✔️ Mysql for docker
- docker를 이용하여 mysql 사용

- 로컬에서 3306, 3307 포트를 사용중이기에 3308 -> 3306으로 맵핑

```
docker pull mysql
                                 
docker run -d -p 3308:3306 --name mysql -e MYSQL_ROOT_PASSWORD=1234 mysql

docker start mysql

docker exec -it mysql bash

bash-5.1# mysql -u root -p
Enter password:

mysql> create database commerce_example;

mysql> use commerce_example;
Database changed
```
> mysql 셋팅 완료
