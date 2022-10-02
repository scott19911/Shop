package com.example.electronicshop.dao;

import com.example.electronicshop.products.CategoryDTO;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.electronicshop.dao.MySqlUserDao.AVATAR_URL;
import static com.example.electronicshop.dao.MySqlUserDao.EMAIL;
import static com.example.electronicshop.dao.MySqlUserDao.FIRST_NAME;
import static com.example.electronicshop.dao.MySqlUserDao.LAST_NAME;
import static com.example.electronicshop.dao.MySqlUserDao.PASSWORD;
import static com.example.electronicshop.dao.MySqlUserDao.RECEIVE_MAILING;
import static com.example.electronicshop.dao.MySqlUserDao.SALT;
import static com.example.electronicshop.dao.MySqlUserDao.USER_ID;


/**
 * The class allows you to retrieve the user from ResultSet
 */
public class ConverterResultSet {
    /**
     * allows you to retrieve the list of user from ResultSet
     *
     * @param resultSet - entered data
     * @return - list of user
     */
    public List<User> getListUser(ResultSet resultSet) {
        List<User> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(USER_ID));
                user.setMailing(resultSet.getBoolean(RECEIVE_MAILING));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setAvatarUdl(resultSet.getString(AVATAR_URL));
                list.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Sorry, cannot load list of users");
        }
        return list;
    }

    /**
     * allows you to retrieve the user from ResultSet
     *
     * @param resultSet - entered data
     * @return - user
     */
    public User getUser(ResultSet resultSet) {
        User user = new User();
        try {
            if (resultSet.next()) {
                user.setPassword(resultSet.getString(PASSWORD));
                user.setSalt(resultSet.getString(SALT));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setId(resultSet.getInt(USER_ID));
                user.setMailing(resultSet.getBoolean(RECEIVE_MAILING));
                user.setAvatarUdl(resultSet.getString(AVATAR_URL));
                return user;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }


}
