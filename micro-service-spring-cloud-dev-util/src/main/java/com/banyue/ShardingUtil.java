package com.banyue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/4/25 22:53
 * @description:
 */
public class ShardingUtil {

    private static final int SHARDING_COUNT = 4; // 分片数量

    private static final String[] DB_URLS = {
            "jdbc:mysql://sharding-1:3306/test?useSSL=false",
            "jdbc:mysql://sharding-2:3306/test?useSSL=false",
            "jdbc:mysql://sharding-3:3306/test?useSSL=false",
            "jdbc:mysql://sharding-4:3306/test?useSSL=false"
    };

    private static final String QUERY_SQL = "SELECT * FROM User WHERE name = ? AND age = ?";

    private static final String INSERT_SQL = "INSERT INTO User (name, age) VALUES (?, ?)";

    public static void main(String[] args) {
        String name = "Alice";
        int age = 20;
        int shard = calculateShard(name); // 根据分片键计算分片编号

        // 通过分片编号选择对应的数据库连接
        try (Connection conn = DriverManager.getConnection(DB_URLS[shard], "root", "password");
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = getReadConnection(name, age);
             PreparedStatement stmt = conn.prepareStatement(QUERY_SQL)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("name") + ", " + rs.getInt("age"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据分片键计算分片编号
    private static int calculateShard(String name) {
        int shard = name.charAt(0) / ('z' - 'a' + 1) * SHARDING_COUNT;
        if (shard >= SHARDING_COUNT) {
            shard = SHARDING_COUNT - 1;
        }
        return shard;
    }

    // 获取可读连接
    private static Connection getReadConnection(String name, int age) throws SQLException {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < SHARDING_COUNT; i++) {
            urls.add(DB_URLS[i]);
        }
        Collections.shuffle(urls); // 打乱顺序，使得读请求分散在各个分片上
        SQLException lastException = null;
        for (String url : urls) {
            try {
                Connection conn = DriverManager.getConnection(url, "root", "password");
                conn.setReadOnly(true);

                // 如果要求强一致性，可以将这里的隔离级别改为 Serializable
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                return conn;
            } catch (SQLException e) {
                lastException = e;
            }
        }
        if (lastException != null) {
            throw lastException;
        } else {
            throw new SQLException("All connection attempts failed");
        }
    }

}
