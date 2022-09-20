package com.example.electronicshop.registration;

import com.example.electronicshop.users.User;

/**
 * Registration form data storage and transfer class
 */
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String captcha;
    private String password;
    private boolean notification;

    /**
     * allows you to get the user First Name
     *
     * @return - user First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * allows you to set the user First Name
     *
     * @param firstName - user First Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * allows you to get the user Last Name
     *
     * @return - user Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * allows you to set the user Last Name
     *
     * @param lastName - user Last Name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * allows you to get the user Email
     *
     * @return - user Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * allows you to set the user Email
     *
     * @param email -user Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * allows you to get the user Password
     *
     * @return - user Password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * creates user class
     *
     * @return - User
     */
    public User getUser() {
        User user = new User();
        user.setEmail(email);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPassword(password);
        return user;
    }

    /**
     * allows you to find out if mailings are necessary
     *
     * @return - true when activate mailings
     */
    public boolean isNotification() {
        return notification;
    }

    /**
     * allows you to set mailings
     *
     * @param notification - true when activate mailings
     */
    public void setNotification(boolean notification) {
        this.notification = notification;
    }
}
