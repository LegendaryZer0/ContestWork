package ru.company.repository;

import java.sql.ResultSet;
import java.util.Map;

public interface MainRepository
{
    public void save(Map<String, Long> extract);
    public ResultSet getAllRecords();
}
