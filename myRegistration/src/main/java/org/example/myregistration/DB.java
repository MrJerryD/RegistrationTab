package org.example.myregistration;

import java.sql.*;

public class DB {
    // Параметры подключения к базе данных
    private final String HOST = "localhost"; // Сервер базы данных
    private final String PORT = "8889"; // Порт для подключения к базе данных
    private final String DB_NAME = "shtogg-java"; // Имя базы данных
    private final String LOGIN = "root"; // Логин пользователя базы данных
    private final String PASSWORD = "root"; // Пароль пользователя базы данных

    private Connection dbConn = null; // Объект подключения к базе данных

    /*
     * @return Connection - объект подключения к базе данных
     * @throws ClassNotFoundException если драйвер базы данных не найден
     * @throws SQLException если возникает ошибка при подключении к базе данных
     */

//    Устанавливает подключение к базе данных
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        // Формирование URL для подключения к базе данных
        String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        // Загрузка драйвера базы данных
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Установка подключения к базе данных
        dbConn = DriverManager.getConnection(url, LOGIN, PASSWORD);
        return dbConn;
    }

    public void isConnected() throws SQLException, ClassNotFoundException {
        // Получение подключения к базе данных
        dbConn = getDbConnection();
        // Проверка валидности подключения
        System.out.println(dbConn.isValid(1000));
    }

    public boolean isExistUser(String login) {
        String sql = "SELECT `id` FROM users WHERE `login` = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, login);

            ResultSet rs = prSt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // регистрация
    public void regUser(String login, String email, String password) throws SQLException, ClassNotFoundException {
        String sql = "insert into users (login, email, password) values (?, ?, ?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, login);
        prSt.setString(2, email);
        prSt.setString(3, password);
        prSt.executeUpdate();
    }
}









