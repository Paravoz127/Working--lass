function validate(form_id,email) {
    var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var address = document.forms[form_id].elements[email].value;
    if(reg.test(address) == false) {
       document.getElementById(email).style.borderColor = 'red';
       return false;
    }
 }