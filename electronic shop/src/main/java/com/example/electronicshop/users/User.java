package com.example.electronicshop.users;

/**
 * User data storage class
 */
public class User {
    /**
     * user first name
     */
    private String firstName;
    /**
     * user last name
     */
    private String lastName;
    /**
     * user email
     */
    private String email;
    /**
     * user password
     */
    private String password;
    /**
     * user id
     */
    private int id;
    /**
     * Does the user want to receive newsletters
     */
    private boolean mailing;
    /**
     * user avatar link
     */
    private String avatarUdl;
    private String salt;

    public User() {
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Create a user with a preset email
     *
     * @param email - user email
     */
    public User(String email) {
        this.email = email;
    }

    /**
     * Let's get the user First Name
     *
     * @return - First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Let's set the user First Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Let's get the user Last Name
     *
     * @return - Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Let's set the user Last Name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Let's get the user email
     *
     * @return - email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Let's set the user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Let's get the user password
     *
     * @return - password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Let's set the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Let's get the user id
     *
     * @return - password
     */
    public int getId() {
        return id;
    }

    /**
     * Let's set the user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Allows you to find out if the user wants to receive mailings
     *
     * @return - true when user wants to receive mailings
     */
    public boolean isMailing() {
        return mailing;
    }

    /**
     * Let's set the user mailing
     *
     * @param mailing - boolean which is responsible for receiving the mailing list
     */
    public void setMailing(boolean mailing) {
        this.mailing = mailing;
    }

    public String getAvatarUrl() {
        return avatarUdl;
    }

    public void setAvatarUdl(String avatarUdl) {
        this.avatarUdl = avatarUdl;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", mailing=" + mailing +
                ", avatarUdl='" + avatarUdl + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
