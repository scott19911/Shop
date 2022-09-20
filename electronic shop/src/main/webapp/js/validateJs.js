/**
 *
 * form field validation
 * @returns {boolean} when valid return true
 */
function validateForm() {
    var name = document.forms["checkout"]["billing_first_name"].value;
    var lastName = document.forms["checkout"]["billing_last_name"].value;
    var email = document.forms["checkout"]["billing_email"].value;
    var password = document.forms["checkout"]["account_password"].value;
    var captcha = document.forms["checkout"]["captcha"].value;
    var regexName = /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$/;
    var regexEmail = /^([_\-\.0-9a-zA-Z]+)@([_\-\.0-9a-zA-Z]+)\.([a-zA-Z]){2,7}$/;
    var regexCaptcha = /^[0-9]{6}$/;
    var validName = valid(name, "First Name is missing or incorrect", regexName, "demo");
    var validLastName = valid(lastName, "Last Name is missing or incorrect", regexName, "demo1");
    var validEmail = valid(email, "Email is missing or incorrect", regexEmail, "demo5");
    var validPassword = validatePassword(password, "demo7");
    var validCaptcha = valid(captcha, "Captcha missing or shorter 6 ",regexCaptcha,"demo8");
    if (validName === false || validLastName === false || validEmail === false ||
         validPassword === false || validCaptcha === false) {
        return false;
    }
}

/**
 * Checks parameters against a regular expression
 * @param parameter - checks parameter
 * @param message - error message
 * @param regex - regular expression to check
 * @param elementError - display element
 * @returns {boolean} - when valid return true
 */
function valid(parameter, message, regex, elementError) {
    if (parameter === "" || !parameter.match(regex)) {
        document.getElementById(elementError).innerHTML = message;
        return false;
    }
    document.getElementById(elementError).innerHTML = "";
    return true;
}

/**
 * password validation
 * @param passwordValue - checks parameter
 * @param element - display element
 * @returns {boolean} - when valid return true
 */
function validatePassword(passwordValue, element) {
    if (passwordValue.length === "") {
        document.getElementById(element).innerHTML = "password is missing";
        return false;
    }
    if (passwordValue.length < 3 || passwordValue.length > 10) {
        document.getElementById(element).innerHTML = "**length of your password must be between 3 and 10";
        return false;
    }
    document.getElementById(element).innerHTML = "";
    return true;
}