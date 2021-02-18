package ru.company.repository;

import ru.company.logger.ExceptionLogger;

import java.sql.*;
import java.util.Map;

public class SimpleRepository implements MainRepository {
    //language=PostgreSQL
    private final String SQL_ADD = "insert into \"uniqWords\" values (?,?)";
    //language=SQL
    private final String SQL_FIND = "select count from \"uniqWords\" where word =?";
    //language=SQL
    private final String SQL_UPDATE = "UPDATE \"uniqWords\" set count =count + ? where word = ? ";
    private final String URL = "jdbc:postgresql://localhost:5432/JavaLabSecondCourseDb";
    private final String PASSWORD = "123456789";
    private final String USERNAME = "postgres";
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SimpleRepository() {
    }

    @Override
    public void save(Map<String, Long> extract) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            extract.forEach((key, value) -> {

                try {
                    if (!key.equals(" ")) {
                        preparedStatement = conn.prepareStatement(SQL_FIND);
                        preparedStatement.setString(1, key);
                        resultSet =preparedStatement.executeQuery();
                        if (!resultSet.next()) {
                            preparedStatement = conn.prepareStatement(SQL_ADD);
                            preparedStatement.setString(1, key);
                            preparedStatement.setLong(2, value);
                            preparedStatement.executeUpdate();
                        }else {
                            preparedStatement = conn.prepareStatement(SQL_UPDATE);
                            preparedStatement.setLong(1,value);
                            preparedStatement.setString(2,key);
                            preparedStatement.executeUpdate();
                        }



                    }
                } catch (SQLException throwables) {
                    ExceptionLogger.getLogger().logException(throwables);
                }
            });

        } catch (SQLException throwables) {
            ExceptionLogger.getLogger().logException(throwables);
        }
    }
}
