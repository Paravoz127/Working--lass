function counter(limit) {
    var count = $('#count');
    var input = $('#input');

    if (input.val().length > limit) {
        input.val(input.val().substr(0, limit));
        count.text(0);
    } else {
        count.text(limit-input.val().length);
    }
}