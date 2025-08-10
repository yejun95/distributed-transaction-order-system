# 주문시스템으로 알아보는 분산트랜잭션
## ✔️ 요구사항
- 주문 데이터를 저장해 한다.

- 재고관리를 해야 한다.

- 포인트를 사용해야 한다.

- 주문, 재고, 포인트 데이터의 정합성이 맞아야 한다.

- 동일한 주문은 1번만 이루어져야 한다.
<br>
<hr>
<br>

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
<br>
<hr>
<br>

## ✔️ redis for docker
- docker를 사용하여 redis 사용

- 분산락 구현을 위한 Lettuce, Redisson 사용 예정

```
docker pull redis

docker run --name myredis -d -p 6379:6379 redis

docker ps
CONTAINER ID   IMAGE     COMMAND                   CREATED         STATUS          PORTS                               NAMES
003d7266f1a4   redis     "docker-entrypoint.s…"   2 seconds ago   Up 1 second     0.0.0.0:6379->6379/tcp              myredis
f1b27c48c8bd   mysql     "docker-entrypoint.s…"   26 hours ago    Up 12 minutes   33060/tcp, 0.0.0.0:3308->3306/tcp   mysql

docker exec -it 003d7266f1a4 redis-cli
