package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Михаил", "Коржиков", (byte) 25);
        userService.saveUser("Елена", "Новикова", (byte) 35);
        userService.saveUser("Мария", "Лютая", (byte) 19);
        userService.saveUser("Иван", "Петров", (byte) 19);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
