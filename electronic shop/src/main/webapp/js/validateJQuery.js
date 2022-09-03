/**
 * form field validation
 */
$(document).ready(function () {
	/**
	 * hide error message on first name
	 */
	$("#first_name").hide();
	let usernameError = true;
	$("#usernames").keyup(function () {
		validateUsername();
	});

	/**
	 * validate first name
	 * @returns {boolean} true when valid
	 */
	function validateUsername() {
		const letters = /^[A-Za-zА-Яа-я]+$/;
		let usernameValue = $("#billing_first_name").val();
		if (usernameValue.length == "" || !usernameValue.match(letters) ) {
			$("#first_name").show();
			usernameError = false;
			return false;
		} else if (usernameValue.length < 1 || usernameValue.length > 15) {
			$("#first_name").show();
			$("#first_name").html("**length of username must be between 1 and 15");
			usernameError = false;
			return false;
		} else {
			$("#first_name").hide();
			usernameError = true;
			return true;
		}
	}

	$("#last_name").hide();
	let lastNameError = true;
	$("#last_name").keyup(function () {
		validateLastName();
	});
	/**
	 * validate last name
	 * @returns {boolean} true when valid
	 */
	function validateLastName() {
		var letters = /^[A-Za-zА-Яа-я]+$/;
		let usernameValue = $("#billing_last_name").val();
		if (usernameValue.length == "" || !usernameValue.match(letters) ) {
			$("#last_name").show();
			lastNameError = false;
			return false;
		} else if (usernameValue.length < 1 || usernameValue.length > 15) {
			$("#last_name").show();
			$("#last_name").html("**length of Last must be between 1 and 15");
			lastNameError = false;
			return false;
		} else {
			$("#last_name").hide();
			lastNameError = true;
			return true;
		}
	}

	$("#address").hide();
	let addresError = true;
	$("#address").keyup(function () {
		validateAddress();
	});
	/**
	 * validate address
	 * @returns {boolean} true when valid
	 */
	function validateAddress() {
		var letters = /[!@#$%^&*()_+\-=\[\]{};:"\\|,.<>\/?]+/;
		let value = $("#billing_address_1").val();
		if (value.length == "" || value.match(letters) ) {
			$("#address").show();
			addresError = false;
			return false;
		} else if (value.length < 1 || value.length > 15) {
			$("#address").show();
			$("#address").html("**length of address must be between 1 and 15");
			addresError = false;
			return false;
		} else {
			$("#address").hide();
			addresError = true;
			return true;
		}
	}

	$("#city").hide();
	let cityError = true;
	$("#city").keyup(function () {
		validateCity();
	});
	/**
	 * validate city
	 * @returns {boolean} true when valid
	 */
	function validateCity() {
		var letters = /^[A-Za-zА-Яа-я]+$/;
		let value = $("#billing_city").val();
		if (value.length == "" || !value.match(letters) ) {
			$("#city").show();
			cityError = false;
			return false;
		} else if (value.length < 1 || value.length > 15) {
			$("#city").show();
			$("#city").html("**length of City must be between 1 and 15");
			cityError = false;
			return false;
		} else {
			$("#city").hide();
			cityError = true;
			return true;
		}
	}

	$("#emailValid").hide();
	 emailError = true;
	$("#billing_email").keyup(function () {
		validateEmail();
	});
	/**
	 * validate email
	 * @returns {boolean} true when valid
	 */
	function validateEmail() {
		const email = document.getElementById("billing_email");
		email.addEventListener("blur", () => {
			let regex = /^([_\-\.0-9a-zA-Z]+)@([_\-\.0-9a-zA-Z]+)\.([a-zA-Z]){2,7}$/;
			let s = email.value;
			if (regex.test(s)) {
				email.classList.remove("is-invalid");
				$("#emailValid").hide();
				emailError = true;
			} else {
				email.classList.add("is-invalid");
				$("#emailValid").show();
				$("#emailValid").html(
					"**Your email must be a valid email"
				);
				emailError = false;
			}
		});
	}

	$("#passcheck").hide();
	let passwordError = true;
	$("#account_password").keyup(function () {
		validatePassword();
	});
	/**
	 * validate password
	 * @returns {boolean} true when valid
	 */
	function validatePassword() {
		let passwordValue = $("#account_password").val();
		if (passwordValue.length == "") {
			$("#passcheck").show();
			passwordError = false;
			return false;
		}
		if (passwordValue.length < 3 || passwordValue.length > 10) {
			$("#passcheck").show();
			$("#passcheck").html(
				"**length of your password must be between 3 and 10"
			);
			$("#passcheck").css("color", "red");
			passwordError = false;
			return false;
		} else {
			$("#passcheck").hide();
		}
	}

	$("#postcode").hide();
	let postcodeError = true;
	$("#postcode").keyup(function () {
		validateUsername();
	});
	/**
	 * validate postcode
	 * @returns {boolean} true when valid
	 */
	function validatePostcode() {
		let postcodeValue = $("#billing_postcode").val();
		if (postcodeValue.length == "") {
			$("#postcode").show();
			postcodeError = false;
			return false;
		} else if (postcodeValue.length < 5 || postcodeValue.length > 8) {
			$("#postcode").show();
			$("#postcode").html("**length of postcode must be between 5 and 7");
			postcodeError = false;
			return false;
		} else {
			$("#postcode").hide();
			postcodeError = true;
			return true;
		}
	}

	$("#number").hide();
	let numberError = true;
	$("#number").keyup(function () {
		validateNumber();
	});
	/**
	 * validate phone number
	 * @returns {boolean} true when valid
	 */
	function validateNumber() {
		var letters = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
		let value = $("#billing_phone").val();
		if (value.length == "" || !value.match(letters) ) {
			$("#number").show();
			numberError = false;
			return false;
		} else if (value.length < 3 || value.length > 15) {
			$("#number").show();
			$("#number").html("**length of Phone number must be between 3 and 15");
			numberError = false;
			return false;
		} else {
			$("#number").hide();
			numberError = true;
			return true;
		}
	}

	/**
	 * on button click checks if all fields valid
	 */
	$("#place_order").click(function () {
		validateUsername();
		validatePassword();
		validateEmail();
		validatePostcode();
		validateLastName();
		validateAddress();
		validateCity();
		validateNumber();
		if (
			usernameError === true &&
			passwordError === true &&
			postcodeError === true &&
			lastNameError === true &&
			addresError === true &&
			cityError === true &&
			numberError === true &&
			emailError === true
		) {
			return true;
		} else {
			return false;
		}
	});
});