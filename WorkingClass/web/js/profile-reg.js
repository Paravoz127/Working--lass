function validate() {
    var fname = $('#fname');
    var sname = $('#sname');
    var submit = $('#button');
    var fnameError = $('#fname-error');
    var snameError = $('#sname-error');

    var vn1 = validateName(fname, fnameError);
    var vn2 = validateName(sname, snameError);

    if (vn1 && vn2) {
        submit.attr('disabled', false);
    } else {
        submit.attr('disabled', true);
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