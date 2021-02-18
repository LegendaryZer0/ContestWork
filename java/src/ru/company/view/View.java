package ru.company.view;

import ru.company.logger.ExceptionLogger;
import ru.company.service.IService;

import java.sql.ResultSet;
import java.sql.SQLException;


public class View implements Iview {
    private final IService service;
    private ResultSet resultSet;

    public View(IService service) {
        this.service = service;
    }


    @Override
    public void show(String url) {
        service.execute(url);
        resultSet = service.getResult();
        while (true) {
            try {
                if (!resultSet.next()) {break;}
                System.out.println(resultSet.getString(1)+" = "+ resultSet.getLong(2));
            } catch (SQLException throwables) {
                ExceptionLogger.getLogger().logException(throwables);
            }

        }
    }
}
