package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String CONFIG_FILE = "config.properties";

    // Метод для установки соединения с базой данных
    public static Connection getConnection() {
        Connection connection = null;

        try (InputStream input = Util.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            Properties prop = new Properties();

            // Загрузка конфигурационного файла
            if (input == null) {
                System.out.println("Sorry, unable to find " + CONFIG_FILE);
                return null;
            }

            prop.load(input);

            // Чтение параметров из конфигурационного файла
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            // Регистрация драйвера JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Установка соединения
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace(); // Обработка ошибок
        }

        return connection;
    }
}

