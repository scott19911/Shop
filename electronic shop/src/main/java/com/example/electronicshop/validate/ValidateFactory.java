package com.example.electronicshop.validate;

/**
 * Returns the required validator depending on the application context
 */
public class ValidateFactory {
    /**
     * Possible values of the application context for captcha storage
     */
    public static final String COOKIE = "cookie";
    public static final String FIELDS = "field";

    /**
     * Returns the required validator depending on the application context
     *
     * @param type - application context
     * @return - validator
     */
    public ValidatesRegistrationForm createValidates(String type) {
        switch (type) {
            case COOKIE:
                return new ValidateCookie();
            case FIELDS:
                return new ValidateFields();
            default:
                return new ValidateSession();
        }
    }
}
