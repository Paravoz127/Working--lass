<#macro css_js message_value="">
    <link rel="stylesheet" href="css/promotion.css" />
    <script src="js/promotion.js"></script>
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById("message").value = "${message_value}";
        }
    </script>
</#macro>

<#macro info form_action submit_info >
    <div class="pers-info">
        <form class="pers-info" method="POST" action=${form_action} />
            <label class="txt">Factor:</label>
            <input autocomplete="off" class="name" name="factor_name" placeholder="Enter factor name">
            <label class="txt">Salary($):</label>
            <input autocomplete="off" type="Number" class="name" name="value" placeholder="Enter value">
            <label class="txt">Message:</label>
            <textarea autocomplete="off" id="message" class="message" name="message" placeholder="Enter Message"></textarea>
            <button class="submit-button" type="submit" ${submit_info} />Save</button>
        </form>
    </div>
</#macro>
