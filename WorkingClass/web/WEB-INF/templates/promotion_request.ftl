<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/promotion.css" />
    <script src="js/promotion.js"></script>
</#macro>
<#macro info>
    <div class="pers-info">
        <form class="pers-info" action="/WorkingClass_war_exploded/promotion" method="POST">
            <label class="txt">Message:</label>
            <textarea autocomplete="off" class="message" placeholder="Enter Message" name="message"></textarea>
            <button class="submit-button" type="submit">Save</button>
        </form>
    </div>
</#macro>

<@display_page />