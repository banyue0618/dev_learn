package com.banyue.demo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author zhangsip
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024/10/16
 */
public class FileTransferDemo {

    public static void main(String[] args) {
        // 从MySQL数据库查询需要的数据
        Map<String, String> valueMap = fetchDataFromMySQL();

        // 读取JSON文件
        String json = readFile("D:/learn_code/dataease/mapFiles/full/000/000000000_full.json");

        // 使用fastjson解析JSON数据
        JSONObject jsonObject = JSON.parseObject(json);

        JSONArray features = jsonObject.getJSONArray("features");

        // 迭代features 替换指定字段的value
        for (int i = 0; i < features.size(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            String langname2 = properties.getString("name");
            if (valueMap.get(langname2.toLowerCase()) != null) {
                properties.put("name", valueMap.get(langname2.toLowerCase()));
                // 将修改后的数据重新设置到原来的位置
                feature.put("properties", properties);
            }
        }
        // 替换了数组中的数据后，重新设置到JSON对象中
        jsonObject.put("features", features);

        // 将修改后的JSON数据写回文件
        writeToFile(jsonObject.toJSONString(), "D:/learn_code/dataease/mapFiles/full/000/000000000_full2.json");
    }

    private static Map<String, String> fetchDataFromMySQL() {
        Map<String, String> value = new HashMap<>();
        // 连接数据库并查询数据
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gtc_config", "root", "root123456");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT langname2,langname1 FROM tb_ncode WHERE pncode is null");

            while (rs.next()) {
                value.put(rs.getString("langname2").toLowerCase(),rs.getString("langname1"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    private static String readFile(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private static void writeToFile(String content, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
