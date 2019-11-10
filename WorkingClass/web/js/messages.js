function ajaxSearch() {
    var filter;
    if (document.getElementById('0').checked) {
        filter = "0";
    } else if (document.getElementById('1').checked) {
        filter = "1";
    } else {
        filter = "-1"
    }
    $.ajax({
        url: "/WorkingClass_war_exploded/search",
        data: {"String": $("#dropdown").val(),
            "Filter": filter
        },
        dataType: "json",
        success: function (msg) {
            if (msg.objects.length > 0) {
                $("#container").html("");
                for (var i = 0; i < msg.objects.length; i++) {
                    var arg = "";
                    arg += "<a href=\"messages?id=" + msg.objects[i].id + "\" class=\"dropdown-item\" href=\"\">"
                    if(msg.objects[i].image != null) {
                        arg += "<img class=\"small-image\" src=\"" + msg.objects[i].image.imagePath + "\"/>";
                    } else {
                        arg += "<img class=\"small-image\" src=\"https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png\"/>";
                    }
                    arg += msg.objects[i].firstName + " " + msg.objects[i].secondName;
                    arg += "</a>";
                    $("#container").append(arg);
                }
            } else {
                $("#container").html("<div class=\"msg\"> <label class=txt> No results </label> </div>");
            }
        }
    })
}
