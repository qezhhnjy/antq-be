package com.qezhhnjy.antq.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RedisInfo {

    private static final Map<String, String> MAP = new HashMap<>();

    static {
        MAP.put("redis_version", "Redis 服务器版本");
        MAP.put("redis_git_sha1", "Git SHA1");
        MAP.put("redis_git_dirty", "Git dirty flag");
        MAP.put("os", "Redis 服务器的宿主操作系统");
        MAP.put("arch_bits", " 架构（32 或 64 位）");
        MAP.put("multiplexing_api", "Redis 所使用的事件处理机制");
        MAP.put("gcc_version", "编译 Redis 时所使用的 GCC 版本");
        MAP.put("process_id", "服务器进程的 PID");
        MAP.put("run_id", "Redis 服务器的随机标识符（用于 Sentinel 和集群）");
        MAP.put("tcp_port", "TCP/IP 监听端口");
        MAP.put("uptime_in_seconds", "自 Redis 服务器启动以来，经过的秒数");
        MAP.put("uptime_in_days", "自 Redis 服务器启动以来，经过的天数");
        MAP.put("lru_clock", " 以分钟为单位进行自增的时钟，用于 LRU 管理");
        MAP.put("connected_clients", "已连接客户端的数量（不包括通过从属服务器连接的客户端）");
        MAP.put("client_longest_output_list", "当前连接的客户端当中，最长的输出列表");
        MAP.put("client_longest_input_buf", "当前连接的客户端当中，最大输入缓存");
        MAP.put("blocked_clients", "正在等待阻塞命令（BLPOP、BRPOP、BRPOPLPUSH）的客户端的数量");
        MAP.put("used_memory", "由 Redis 分配器分配的内存总量，以字节（byte）为单位");
        MAP.put("used_memory_human", "以人类可读的格式返回 Redis 分配的内存总量");
        MAP.put("used_memory_rss", "从操作系统的角度，返回 Redis 已分配的内存总量（俗称常驻集大小）。这个值和 top 、 ps 等命令的输出一致");
        MAP.put("used_memory_peak", " Redis 的内存消耗峰值(以字节为单位)");
        MAP.put("used_memory_peak_human", "以人类可读的格式返回 Redis 的内存消耗峰值");
        MAP.put("used_memory_lua", "Lua 引擎所使用的内存大小（以字节为单位）");
        MAP.put("mem_fragmentation_ratio", "sed_memory_rss 和 used_memory 之间的比率");
        MAP.put("mem_allocator", "在编译时指定的， Redis 所使用的内存分配器。可以是 libc 、 jemalloc 或者 tcmalloc");

        MAP.put("redis_build_id", "redis_build_id");
        MAP.put("redis_mode", "运行模式，单机（standalone）或者集群（cluster）");
        MAP.put("atomicvar_api", "atomicvar_api");
        MAP.put("hz", "redis内部调度（进行关闭timeout的客户端，删除过期key等等）频率，程序规定serverCron每秒运行10次。");
        MAP.put("executable", "server脚本目录");
        MAP.put("config_file", "配置文件目录");
        MAP.put("client_biggest_input_buf", "当前连接的客户端当中，最大输入缓存，用client list命令观察qbuf和qbuf-free两个字段最大值");
        MAP.put("used_memory_rss_human", "以人类可读的方式返回 Redis 已分配的内存总量");
        MAP.put("used_memory_peak_perc", "内存使用率峰值");
        MAP.put("total_system_memory", "系统总内存");
        MAP.put("total_system_memory_human", "以人类可读的方式返回系统总内存");
        MAP.put("used_memory_lua_human", "以人类可读的方式返回Lua 引擎所使用的内存大小");
        MAP.put("maxmemory", "最大内存限制，0表示无限制");
        MAP.put("maxmemory_human", "以人类可读的方式返回最大限制内存");
        MAP.put("maxmemory_policy", "超过内存限制后的处理策略");
        MAP.put("loading", "服务器是否正在载入持久化文件");
        MAP.put("rdb_changes_since_last_save", "离最近一次成功生成rdb文件，写入命令的个数，即有多少个写入命令没有持久化");
        MAP.put("rdb_bgsave_in_progress", "服务器是否正在创建rdb文件");
        MAP.put("rdb_last_save_time", "离最近一次成功创建rdb文件的时间戳。当前时间戳 - rdb_last_save_time=多少秒未成功生成rdb文件");
        MAP.put("rdb_last_bgsave_status", "最近一次rdb持久化是否成功");
        MAP.put("rdb_last_bgsave_time_sec", "最近一次成功生成rdb文件耗时秒数");
        MAP.put("rdb_current_bgsave_time_sec", "如果服务器正在创建rdb文件，那么这个域记录的就是当前的创建操作已经耗费的秒数");
        MAP.put("aof_enabled", "是否开启了aof");
        MAP.put("aof_rewrite_in_progress", "标识aof的rewrite操作是否在进行中");
        MAP.put("aof_rewrite_scheduled", "rewrite任务计划，当客户端发送bgrewriteaof指令，如果当前rewrite子进程正在执行，那么将客户端请求的bgrewriteaof变为计划任务，待aof子进程结束后执行rewrite ");

        MAP.put("aof_last_rewrite_time_sec", "最近一次aof rewrite耗费的时长");
        MAP.put("aof_current_rewrite_time_sec", "如果rewrite操作正在进行，则记录所使用的时间，单位秒");
        MAP.put("aof_last_bgrewrite_status", "上次bgrewrite aof操作的状态");
        MAP.put("aof_last_write_status", "上次aof写入状态");

        MAP.put("total_commands_processed", "redis处理的命令数");
        MAP.put("total_connections_received", "新创建连接个数,如果新创建连接过多，过度地创建和销毁连接对性能有影响，说明短连接严重或连接池使用有问题，需调研代码的连接设置");
        MAP.put("instantaneous_ops_per_sec", "redis当前的qps，redis内部较实时的每秒执行的命令数");
        MAP.put("total_net_input_bytes", "redis网络入口流量字节数");
        MAP.put("total_net_output_bytes", "redis网络出口流量字节数");

        MAP.put("instantaneous_input_kbps", "redis网络入口kps");
        MAP.put("instantaneous_output_kbps", "redis网络出口kps");
        MAP.put("rejected_connections", "拒绝的连接个数，redis连接个数达到maxclients限制，拒绝新连接的个数");
        MAP.put("sync_full", "主从完全同步成功次数");

        MAP.put("sync_partial_ok", "主从部分同步成功次数");
        MAP.put("sync_partial_err", "主从部分同步失败次数");
        MAP.put("expired_keys", "运行以来过期的key的数量");
        MAP.put("evicted_keys", "运行以来剔除(超过了maxmemory后)的key的数量");
        MAP.put("keyspace_hits", "命中次数");
        MAP.put("keyspace_misses", "没命中次数");
        MAP.put("pubsub_channels", "当前使用中的频道数量");
        MAP.put("pubsub_patterns", "当前使用的模式的数量");
        MAP.put("latest_fork_usec", "最近一次fork操作阻塞redis进程的耗时数，单位微秒");
        MAP.put("role", "实例的角色，是master or slave");
        MAP.put("connected_slaves", "连接的slave实例个数");
        MAP.put("master_repl_offset", "主从同步偏移量,此值如果和上面的offset相同说明主从一致没延迟");
        MAP.put("repl_backlog_active", "复制积压缓冲区是否开启");
        MAP.put("repl_backlog_size", "复制积压缓冲大小");
        MAP.put("repl_backlog_first_byte_offset", "复制缓冲区里偏移量的大小");
        MAP.put("repl_backlog_histlen", "此值等于 master_repl_offset - repl_backlog_first_byte_offset,该值不会超过repl_backlog_size的大小");
        MAP.put("used_cpu_sys", "将所有redis主进程在核心态所占用的CPU时求和累计起来");
        MAP.put("used_cpu_user", "将所有redis主进程在用户态所占用的CPU时求和累计起来");
        MAP.put("used_cpu_sys_children", "将后台进程在核心态所占用的CPU时求和累计起来");
        MAP.put("used_cpu_user_children", "将后台进程在用户态所占用的CPU时求和累计起来");
        MAP.put("cluster_enabled", "实例是否启用集群模式");

        for (int i = 0; i < 16; i++) {
            String db = "db" + i;
            MAP.put(db, db + "的key的数量,以及带有生存期的key的数,平均存活时间");
        }
    }

    private String prop;
    private String value;
    private String desc;

    public static RedisInfo populate(Object prop, Object value) {
        RedisInfo info = new RedisInfo();
        info.setProp(String.valueOf(prop));
        info.setValue(String.valueOf(value));
        info.setDesc(MAP.get(String.valueOf(prop)));
        return info;
    }
}
