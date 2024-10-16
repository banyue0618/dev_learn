package com.banyue.demo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 对比不同库中相同表的字段长度是否匹配，然后打印出不匹配的表名跟字段名
 */
public class DatabaseComparatorDemo {

    public static void main(String[] args) {


        String db1Url = "jdbc:mysql://10.10.104.55:9030/gtc_bi_sink";
        String db1Username = "root";
        String db1Password = "";

        String db2Url = "jdbc:mysql://10.10.104.37:6446/";
        String db2Username = "gtc_user";
        String db2Password = "GNkjcvIfHbFljkD2861MR3vRd4lB9l";

        Map<String, Connection> connectionMap = new HashMap<>();

        Map<String, String> resMap = new HashMap<>();

        Map<String, String> failMap = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection(db1Url, db1Username, db1Password);
            String query = "show  tables where Table_type = 'BASE TABLE'";
            Statement stmt1 = conn1.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1 = stmt1.executeQuery(query);

            while (rs1.next()) {
                String tableName = rs1.getString(1);
                System.out.println(tableName);
                if(tableName.contains("nginx") || tableName.contains("tpo_ogquery_log") || tableName.contains("tpo_ogquery_log_sub")){
                    continue;
                }
                String db = "gtc_" +tableName.substring(0, tableName.indexOf('_'));
                Connection conn2 = null;
                if(connectionMap.get(db) == null){
                    if(conn2 != null){
                        conn2.close();
                    }
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(db2Url + db, db2Username, db2Password);
                    connectionMap.put(db, connection);
                }
                conn2 = connectionMap.get(db);

                String tableQuery = "DESCRIBE " + tableName;

                PreparedStatement preparedStatement = conn1.prepareStatement(tableQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet fields1 = preparedStatement.executeQuery(tableQuery);

                PreparedStatement preparedStatement2 = conn2.prepareStatement(tableQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                ResultSet fields2 = null;
                try {
                    fields2 = preparedStatement2.executeQuery(tableQuery);
                } catch (SQLException e) {
                    failMap.put(tableName, "1");
                    continue;
                }

                Map<String,String> fieldMap1 = new HashMap<>();
                Map<String,String> fieldMap2 = new HashMap<>();
                while (fields1.next()){
                    String fieldType1 = fields1.getString("Type");
                    if(fieldType1.startsWith("varchar") || fieldType1.startsWith("VARCHAR")){
                        fieldMap1.put(fields1.getString("Field"), fieldType1);
                    }
                }
                while (fields2.next()){
                    fieldMap2.put(fields2.getString("Field"), fields2.getString("Type"));
                }
                boolean needChange = false;
                for (Map.Entry<String, String> entry : fieldMap1.entrySet()) {
                    int length1 = extractVarcharLength(entry.getValue());
                    if(fieldMap2.get(entry.getKey()) != null){
                        int length2 = extractVarcharLength(fieldMap2.get(entry.getKey()));
                        if (length1 != length2 * 3) {
                            needChange = true;
                            System.out.println("tableName: " + tableName + "|============>" + "fieldName1: " + entry.getKey() + " ,length1:" + length1+ " ,length2:" + length2);
                        }
                    }
                }
                if(!needChange){
                    resMap.put(tableName, "1");
                }
            }
            resMap.keySet().forEach(System.out::print);
            System.out.println("==========================================");
            failMap.keySet().forEach(System.out::print);
            conn1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int extractVarcharLength(String fieldType) {
        // Example input: varchar(255)
        String[] parts = fieldType.split("[()]");
        if (parts.length > 1) {
            return Integer.parseInt(parts[1]);
        }
        return -1;
    }
}