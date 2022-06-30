# Docker 部署记录

`docker` `centos`

- docker

```sh
// 下载docker
curl -sSL https://get.daocloud.io/docker | sh
// 启动docker
systemctl start docker
// 配置docker自启动
systemctl enable docker
// 取消自启动
systemctl disable docker

// docker添加容器自启动
docker update --restart=always <容器名>
```

- 防火墙

```
// 开放端口
firewall-cmd --zone=public --add-port=5672/tcp --permanent   # 开放5672端口
firewall-cmd --zone=public --remove-port=5672/tcp --permanent  #关闭5672端口
firewall-cmd --reload   # 配置立即生效

// 查看防火墙所有开放的端口
firewall-cmd --zone=public --list-ports

// 关闭防火墙
systemctl stop firewalld.service

// 查看防火墙状态
firewall-cmd --state

// 查看监听的端口
// PS:centos7默认没有 netstat 命令，需要安装 net-tools 工具，yum install -y net-tools
netstat -lnpt

// 检查端口被哪个进程占用
netstat -lnpt |grep 5672

// 查看进程的详细信息
ps 6832
```

- mysql

```sh
// 拉q镜像
docker pull mysql:5.7

// 运行
docker run -p 3306:3306 --name mysql5.7 \
-v /docker-data/mysql/log:/var/log/mysql \
-v /docker-data/mysql/data:/var/lib/mysql \
-v /docker-data/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=Fu214849135sl \
-d mysql:5.7

// 修改mysql配置
vim /docker-data/mysql/conf/my.cnf

// 不存在则创建并添加以下内容
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
init_connect='SET collation_connection=utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve

// 重启
docker restart mysql5.7

// 复制文件
docker cp antq.sql mysql5.7:/

// 进入容器
docker exec -it mysql5.7 bash
```

- redis

```sh
docker pull redis

// touch 创建文件是因为该文件镜像中不存在，直接映射会失败
mkdir -p /docker-data/redis/conf
touch /docker-data/redis/conf/redis.conf

docker run -p 6379:6379 --name redis -v /docker-data/redis/data:/data \
-v /docker-data/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf

// 使用redis镜像执行redis-cli
docker exec -it redis redis-cli

// redis.conf文件中添加 appendonly yes ,设置AOF
```

- nacos

```sh
docker pull nacos/nacos-server

mkdir -p /docker-data/nacos/logs
#配置application.properties文件

docker  run \
--name nacos -d \
-p 8848:8848 \
--restart=always \
-e JVM_XMS=200m \
-e JVM_XMX=200m \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-v /docker-data/nacos/logs:/home/nacos/logs \
-v /docker-data/nacos/conf/application.properties:/home/nacos/conf/application.properties \
nacos/nacos-server

# nacos新版本增加了以下字段
ALTER TABLE config_info ADD COLUMN `encrypted_data_key` text NOT NULL COMMENT '秘钥';
ALTER TABLE config_info_beta ADD COLUMN `encrypted_data_key` text NOT NULL COMMENT '秘钥';
ALTER TABLE his_config_info ADD COLUMN `encrypted_data_key` text NOT NULL COMMENT '秘钥';
```

- nginx

```sh
docker pull nginx

mkdir -p /docker-data/nginx/conf/conf.d
mkdir -p /docker-data/nginx/log
mkdir -p /docker-data/nginx/html

// 可以通过先运行nginx拿到容器里面默认生成的对应文件，然后再映射启动
docker run --name nginx -p 80:80 -d nginx
docker cp 124136acacc3:/etc/nginx/conf.d /docker-data/nginx
docker cp 124136acacc3:/etc/nginx/nginx.conf /docker-data/nginx
docker cp 124136acacc3:/usr/share/nginx/html /docker-data/nginx

# 拷贝完之后就停止并删除容器
docker stop 124136acacc3
docker rm 124136acacc3

docker run -p 80:80 --name nginx --restart=always -v /docker-data/nginx/nginx.conf:/etc/nginx/nginx.conf -v /docker-data/nginx/conf.d:/etc/nginx/conf.d -v /docker-data/nginx/html:/usr/share/nginx/html -v /docker-data/nginx/log:/var/log/nginx -d nginx
```

- minio对象存储

```shell
docker run --privileged -p 11006:9000 -p 11007:9090 --name minio --restart=always \
-v /docker-data/minio/data:/data -v /docker-data/minio/config:/root/.minio \
-e "MINIO_ACCESS_KEY=qezhhnjy" -e "MINIO_SECRET_KEY=Fu214849135sl" \
--privileged=true -d minio/minio server /data --console-address ":9000" --address ":9090" 
```

- rabbitmq
```shell
docker pull rabbitmq
docker run -d --name rabbitmq --restart=always -p 5672:5672 -p 15672:15672 -p 1883:1883 -p 15675:15675 -v /docker-data/rabbitmq:/var/lib/rabbitmq rabbitmq
// 开启web服务
docker exec -it rabbitmq rabbitmq-plugins enable rabbitmq_management
// 开启mqtt功能 端口1883
docker exec -it rabbitmq rabbitmq-plugins enable rabbitmq_mqtt
// 开启mqtt-over-websocket 端口15675
docker exec -it rabbitmq rabbitmq-plugins enable rabbitmq_web_mqtt
```

- `Centos`增加虚拟内存

http://www.moguit.cn/#/info?blogUid=36ee5efa56314807a9b6f1c1db508871

```sh
// 查看磁盘使用情况
free -h

// 添加Swap分区
// dev/zero是Linux的一种特殊字符设备(输入设备)，可以用来创建一个指定长度用于初始化的空文件，如临时交换文件，该设备无穷尽地提供0，可以提供任何你需要的数目。 
// bs=1024  ：单位数据块（block）同时读入/输出的块字节大小为1024  个字节即1KB，bs(即block size)。
// count = 4194304 表示的是4G
// 具体计算公式为：1KB * 4194304 =1KB *1024(k)*1024*4 = 4194304 =4G
// 执行完命令后，会进行4G读写操作，所以会有一些卡顿
dd if=/dev/zero of=/var/swapfile bs=1024 count=4194304

// 对交换文件格式化并转换为swap分区
mkswap /var/swapfile

// 挂载并激活分区
swapon /var/swapfile

// 查看新swap分区是否正常添加并激活使用
free -h

// 修改 fstab 配置，设置开机自动挂载该分区
echo  "/var/swapfile   swap  swap  defaults  0  0" >>  /etc/fstab

// 查看是否已经使用了交换内存
top

// 更改Swap配置
一般默认的情况，当我们开启交换虚拟内存空间后，默认好像是当内存使用百分50的时候，就会开始使用交换空间，这样就会造成一个情况，就是本身物理内存还没有使用完成， 就去使用虚拟内存，这样肯定会影响我们的使用效率，那么我们怎么避免这个情况的发生呢？

答案就是：可以通过swappiness值进行管理，swappiness表示系统对Swap分区的依赖程度，范围是0~100，数值越大，依赖程度越高，也就是越高越会使用Swap分区。

所以，我们现在并不希望我们的机器过度依赖Swap分区，只有当我们 负载超过某个百分比的时候，才使用交换空间，所以这也决定了，我们这个值并不是非常大，一般设置 10 ~50 左右。

当然如果小伙伴的是SSD的话，那么这个值可以稍微大一些。

// 查看当前的swappiness数值
cat /proc/sys/vm/swappiness

// 修改swappiness值，这里以10为例
sysctl vm.swappiness=10

// 设置永久有效，重启系统后生效
echo "vm.swappiness = 10"  >>  /etc/sysctl.conf

// swap分区的删除
// 停止正在使用swap分区
swapoff /var/swapfile
// 删除swap分区文件
rm -rf /var/swapfile
// 删除或注释掉我们之前在fstab文件里追加的开机自动挂载配置内容
vim    /etc/fstab

/把下面内容删除
/var/swapfile   swap  swap  defaults  0  0
```

---

### 接下来的开发计划

- [x] `jimureport`报表
- [ ] [jeecg](https://gitee.com/jeecg/jeecg-boot.git) 仿照`jeecg`的开发功能
- [ ] [skywalking](https://skywalking.apache.org/zh/2020-04-19-skywalking-quick-start/) `skywalking`分布式链路追踪
- [x] `flowable`工作流开发工具
- [x] `quartz`定时任务

- [ ] `seata`分布式事务处理器
- [x] `druid`监控
- [ ] [easy rule](https://github.com/j-easy/easy-rules) 规则引擎
- [x] rabbit和基于rabbit实现的mqtt  
- [ ] 其他...
