package org.nrt.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionChecker {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //проверка успешности подключения к PostgreSQL
    public boolean checkConnection(){
        try{
            // при выполнении элементарного запроса к подключенной БД вернём true
            jdbcTemplate.execute("SELECT 1");
            return true;
        }catch (Exception e){
            // иначе вывод в логи исключения
            e.printStackTrace();
            return false;
        }
    }
}
