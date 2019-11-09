function dropValueWithSearch() {
    var submit = $("#submit");
    submit.attr('value', "");
    ajaxSearch();
}

function ajaxSearch() {
    $.ajax({
        url: "/WorkingClass_war_exploded/search",
        data: {"String": $("#dropdown").val(),
               "Filter": "0"
        },
        dataType: "json",
        success: function (msg) {
            if (msg.objects.length > 0) {
                $("#dropdown-menu").html("");
                for (var i = 0; i < msg.objects.length; i++) {
                    var arg = "";
                    arg += "<div id=\"" + msg.objects[i].id + "\" onclick=\"onDropDown(this)\" class=\"dropdown-item\" href=\"\">"
                    if(msg.objects[i].image != null) {
                        arg += "<img class=\"small-image\" src=\"" + msg.objects[i].image.imagePath + "\"/>";
                    } else {
                        arg += "<img class=\"small-image\" src=\"https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png\"/>";
                    }
                    arg += msg.objects[i].firstName + " " + msg.objects[i].secondName;
                    arg += "</div>";
                    $("#dropdown-menu").append(arg);
                }
            } else {
                $("#dropdown-menu").html("<div class=\"msg\"> <label class=txt> No results </label> </div>");
            }
        }
    })
}

function onDropDown(elem) {
    var dropdown = $("#dropdown");
    dropdown.val(elem.innerText.trim());
    var submit = $("#submit");
    submit.attr('value', elem.id);
}