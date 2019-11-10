function validate(){
    var email = $('#email');
    var emailError = $('#email-error');
    var submit = $('#button-login');
    if (validateEmail(email, emailError)) {
        submit.attr('disabled', false);
        submit.css('background-color','rgb(55, 207, 60)');
    } else {
        submit.attr('disabled', true);
        submit.css('background', '#bdbdbd');
    }
};

function validateEmail(email, emailError) {
    var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (reg.test(email.val()) == false) {
        emailError.text("Invalid Email");
        emailError.css('visibility', 'visible');
        return false;
    } else {
        emailError.css('visibility', 'hidden')
        return true;
    }
}
