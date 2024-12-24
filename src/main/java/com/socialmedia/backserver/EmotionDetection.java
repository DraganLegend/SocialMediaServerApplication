package com.socialmedia.backserver;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EmotionDetection {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/mydb"; // 替換為您的資料庫連線資訊
        String username = "root"; // 替換為您的 MySQL 使用者名稱
        String password = "Skills39"; // 替換為您的 MySQL 密碼
        String csvFilePath = "/Users/draganlegend/java_project/FinalProject/emotion_dataset_raw.csv"; // 替換為您的 CSV 文件路徑

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String insertQuery = "INSERT INTO EmotionData (emotion, text) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
                String[] line;
                boolean skipHeader = true;
                int batchSize = 1000; // 每批次插入 1000 行數據
                int count = 0;

                while ((line = reader.readNext()) != null) {
                    if (skipHeader) {
                        skipHeader = false; // 跳過標題行
                        continue;
                    }

                    // 1. 獲取 CSV 的 emotion 和 text 欄位數據
                    String emotion = line[0]; // 第一欄為 emotion
                    String text = line[1];    // 第二欄為 text

                    // 2. 設置參數到 PreparedStatement
                    preparedStatement.setString(1, emotion);
                    preparedStatement.setString(2, text);
                    preparedStatement.addBatch();
                    count++;

                    // 3. 當批量插入達到設定值時，執行批量插入
                    if (count % batchSize == 0) {
                        preparedStatement.executeBatch();
                        System.out.println("Inserted " + count + " rows...");
                    }
                }

                // 4. 插入剩餘數據
                preparedStatement.executeBatch();
                System.out.println("Inserted total " + count + " rows successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}