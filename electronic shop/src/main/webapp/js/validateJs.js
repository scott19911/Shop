/**
 *
 * form field validation
 * @returns {boolean} when valid return true
 */
function validateForm() {
    var name = document.forms["checkout"]["billing_first_name"].value;
    var lastName = document.forms["checkout"]["billing_last_name"].value;
    var address = document.forms["checkout"]["billing_address_1"].value;
    var city = document.forms["checkout"]["billing_city"].value;
    var postcode = document.forms["checkout"]["billing_postcode"].value;
    var email = document.forms["checkout"]["billing_email"].value;
    var phone = document.forms["checkout"]["billing_phone"].value;
    var password = document.forms["checkout"]["account_password"].value;
    var regexName = /^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$/;
    var regexAddress = /[!@#$%^&*()_+\-=\[\]{};:"\\|,.<>\/?]+/;
    var regexPostcode = /^[A-Za-z0-9]+$/;
    var regexEmail = /^([_\-\.0-9a-zA-Z]+)@([_\-\.0-9a-zA-Z]+)\.([a-zA-Z]){2,7}$/;
    var regexPhone = /^(\+[\d]{1,5}|0)?[7-9]\d{9}$/;
    var validName = valid(name, "First Name is missing or incorrect", regexName, "demo");
    var validLastName = valid(lastName, "Last Name is missing or incorrect", regexName, "demo1");
    var validAddress = valid(address, "Address is missing or incorrect", regexAddress, "demo2");
    var validCity = valid(city, "City is missing or incorrect", regexName, "demo3");
    var validEmail = valid(email, "Email is missing or incorrect", regexEmail, "demo5");
    var validPhone = valid(phone, "Phone number is missing or incorrect", regexPhone, "demo6");
    var validPassword = validatePassword(password, "demo7");
    var validPostcode = validatePostcode(postcode, regexPostcode, "demo4");
    if (validName === false || validLastName === false || validAddress === false ||
        validCity === false || validPostcode === false || validEmail === false ||
        validPhone === false || validPassword === false) {
        return false;
    }
}

/**
 * * zip code validation
 * @param parameter - validate field
 * @param regex -regular expression to check
 * @param elementError - display element
 * @returns {boolean} - when valid return true
 */
function validatePostcode(parameter, regex, elementError) {
    if (parameter === "" || !parameter.match(regex) || parameter.length < 5 || parameter > 7) {
        document.getElementById(elementError).innerHTML = "Postcode is missing or incorrect." +
            " Length of postcode must be between 5 and 7";
        return false;
    }
    document.getElementById(elementError).innerHTML = "";
    return true;
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
    return true;
}