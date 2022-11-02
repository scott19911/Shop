package com.example.electronicshop.users;

/**
 * Contains the necessary data about the user
 */
public class SpecificUser {
    /**
     * User first name
     */
    private String firstName;
    /**
     * user avatar link
     */
    private String avatarUrl;
    /**
     * user id
     */
    private int userId;
    private String userRole;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
