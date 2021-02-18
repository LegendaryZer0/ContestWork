package ru.company.service;

import java.sql.ResultSet;
import java.util.Map;

public interface IService {
    public void execute(String url);
    public ResultSet  getResult();
}
