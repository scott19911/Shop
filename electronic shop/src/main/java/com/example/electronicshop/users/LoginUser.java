package com.example.electronicshop.users;

/**
 * Stores information about the user required for authorization
 */
public class LoginUser {
    private String email;
    private String salt;
    private String password;
    private int userId;
    private String avatarUrl;
    private String firstName;
    private String userRole;
    private boolean blocked;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public SpecificUser getSpecificUser() {
        SpecificUser specificUser = new SpecificUser();
        specificUser.setFirstName(firstName);
        specificUser.setAvatarUrl(avatarUrl);
        specificUser.setUserId(userId);
        specificUser.setUserRole(userRole);
        return specificUser;
    }
}
