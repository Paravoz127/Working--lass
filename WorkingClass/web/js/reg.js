function validate(){
    var fname = $('#fname');
    var sname = $('#sname');
    var email = $('#email');
    var password = $('#password')
    var confPassword = $('#password2')
    var email = $('#email');
    var date = $("#bdate")
    var emailError = $('#email-error');
    var fnameError = $('#fname-error');
    var snameError = $('#sname-error');
    var passwordError = $('#password-error')
    var passwordError2 = $('#password-error2')
    var dateError = $('#date-error')
    var submit = $('#button-login');

    var vn1 = validateName(fname, fnameError);
    var vn2 = validateName(sname, snameError);
    var ve = validateEmail(email, emailError);
    var vp = validatePasswords(password, confPassword, passwordError2);
    var vp2 = validatePassword(password, passwordError);
    var vd = validateDate(date, dateError);

    if (vn1 && vn2 && ve && vp && vd && vp2) {
        submit.attr('disabled', false);
        submit.css('background-color','rgb(55, 207, 60)');
    } else {
        submit.attr('disabled', true);
        submit.css('background', '#bdbdbd');
    }
};

function validateDate(date, dateError) {
    dateError.css('visibility', 'hidden');
    if (date.val().split("-")[0] < 1900 || date.val().split("-")[0] > new Date().getFullYear() - 18) {
        if (date.val() != "") {
            dateError.text("Year should be more than 1900 and less than " + (new Date().getFullYear() - 18));
            dateError.css('visibility', 'visible');
        }
        return false;
    }
    return true;
}

function validateEmail(email, emailError) {
    var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (email.val() == "") {
        emailError.css('visibility', 'hidden')
        return true;
    }

    if (reg.test(email.val()) == false) {
        emailError.text("Invalid Email");
        emailError.css('visibility', 'visible');
        return false;
    } else {
        emailError.css('visibility', 'hidden')
        return true;
    }
}

function validateName(name, nameError) {
    var reg = /^[A-Z][a-z]+$/;

    if (name.val() == "") {
        nameError.css('visibility', 'hidden')
        return true;
    }

    if (reg.test(name.val()) == false) {
        nameError.text("Invalid Name")
        nameError.css('visibility', 'visible');
        return false;
    } else {
        nameError.css('visibility', 'hidden');
        return true;
    }
}

function validatePasswords(password, confPassword, passwordError) {
    if (password.val() != confPassword.val()) {
        passwordError.text("Passwords do not match");
        passwordError.css('visibility', 'visible');
        return false;
    } else {
        passwordError.css('visibility', 'hidden');
        return true;
    }
}

function validatePassword(password, passwordError) {
    passwordError.css('visibility', 'hidden');
    if (password.val().length < 8) {
        if (password.val().length != 0) {
            passwordError.text("Password length should be more or equal to 8");
            passwordError.css('visibility', 'visible');
        }
        return false;
    }
    return true;
}