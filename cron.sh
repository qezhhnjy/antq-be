#!/bin/bash

jar=jpx-admin-1.0-SNAPSHOT.jar
jarPath=/data/jpx-be
java=/opt/jdk1.8.0_261/bin/java
jvm="-Xms1024m -Xmx1024m"
spring="--spring.profiles.active=prod"
log=/data/jpx-admin/jpx-admin/info.log

pid=$(ps -ef | grep ${jar} | grep -v grep | awk '{print $2}')
num=$(ps -ef | grep ${jar} | grep -v grep | awk '{print $2}' | wc -l)
start() {
  echo '开始执行监控脚本'$(date +'%Y-%m-%d %H:%M:%S')
  if [[ ${num} -eq 0 ]]; then
    echo '进程不存在,执行重启'$(date +'%Y-%m-%d %H:%M:%S')
    nohup ${java} -jar ${jvm} ${jarPath}/${jar} ${spring} >/dev/null 2>&1 &
  fi
}

stop() {
  echo '开始执行停止脚本'$(date +'%Y-%m-%d %H:%M:%S')
  if [ -n "${pid}" ]; then
    kill -9 "${pid}"
    echo '进程终止=>'"${pid}"
  fi
}

pid() {
  echo "${pid}"
}

log(){
  tail -f ${log}
}

case $1 in
start)
  start
  ;;
stop)
  stop
  ;;
pid)
  pid
  ;;
restart)
  stop
  start
  log
  ;;
log)
  log
  ;;
*)
  echo '请输入正确的命令:start/stop/restart/pid/log'
  exit 0
  ;;
esac
