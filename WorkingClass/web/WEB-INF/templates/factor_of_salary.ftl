<#include "authenticated.ftl" />

<#macro message_value></#macro>
<#macro submit_info></#macro>
<#macro form_action></#macro>

<#macro css_js>
    <link rel="stylesheet" href="css/promotion.css" />
    <script src="js/promotion.js"></script>
    <script type="text/javascript">
        window.onload = function() {
            document.getElementById("message").value = "<@message_value />";
        }
    </script>
</#macro>

<#macro info>
    <div class="pers-info">
        <form class="pers-info" method="POST" action=<@form_action />>
            <label class="txt">Factor:</label>
            <input class="name" name="factor_name" placeholder="Enter factor name">
            <label class="txt">Salary:</label>
            <input class="name" name="value" placeholder="Enter value">
            <label class="txt">Message:</label>
            <textarea id="message" class="message" name="message" placeholder="Enter Message"></textarea>
            <button class="submit-button" type="submit" <@submit_info />>Save</button>
        </form>
    </div>
</#macro>