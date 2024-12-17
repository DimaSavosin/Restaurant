package ru.kpfu.servlets.Repositories;
import ru.kpfu.servlets.models.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    User getUserById(int id);
    int getUserIdByEmail(String email) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
    boolean isEmailValid(String email);
}
