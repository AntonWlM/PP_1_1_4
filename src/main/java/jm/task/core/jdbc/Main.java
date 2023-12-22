package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Misha", "Mushkin", (byte) 5);
        userService.saveUser("Vasya", "Pupkin", (byte) 18);
        userService.saveUser("Pupa", "Budkin", (byte) 120);
        userService.saveUser("Olya", "Lukoil", (byte) 59);
        List <User> userList = userService.getAllUsers();
//        userService.removeUserById(2);//todo: метод просто не рабочий - в консоль с ним/без него выводится одно и то же
        System.out.println(userList);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
