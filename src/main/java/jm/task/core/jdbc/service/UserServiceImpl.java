package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {


    UserDaoJDBCImpl userService = new UserDaoJDBCImpl();//todo: вносим/инициализируем все переменные в класс - через конструктор

    @Override
    public void createUsersTable() {
        userService.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userService.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userService.saveUser(name, lastName, age);
    }//todo: на следующих задачах - избавляемся от примитивов

    @Override
    public void removeUserById(long id) {
        //todo: на ВСЕХ методах service - должно быть логирование (иммитация логирования - через sout)
        userService.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public void cleanUsersTable() { userService.cleanUsersTable(); }
}
