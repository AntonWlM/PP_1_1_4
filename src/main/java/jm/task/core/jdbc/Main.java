package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {

    //todo: codeStyle - лишние импорты (быстро удаляются горячими клавишами)
    //todo: README - нужно заполнить условиями задачи, если придется обратиться к ней через месяц - можно быстро ознакомиться
    //todo: DB - нужно давать логическое кратное наименование ..pp_1_1_4 - нет

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Misha", "Mushkin", (byte) 5);
        userService.saveUser("Vasya", "Pupkin", (byte) 18);
        userService.saveUser("Pupa", "Budkin", (byte) 120);
        userService.saveUser("Olya", "Lukoil", (byte) 59);
        List <User> userList = userService.getAllUsers();
        // TODO: нет реализации п.5 Удаление User из таблицы ( по id )
        System.out.println(userList);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
