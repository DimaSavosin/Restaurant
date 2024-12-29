package ru.kpfu.Repositories;
import ru.kpfu.models.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDAO extends CrudDAO<User> {
   Optional<User> getUserById(int id);
    int getUserIdByEmail(String email) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
    boolean isEmailValid(String email);
}
