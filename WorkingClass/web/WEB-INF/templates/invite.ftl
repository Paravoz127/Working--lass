<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/invite.css" />
    <script src="js/invite.js"></script>
</#macro>

<#macro info>
    <div class="pers-info">
        <form class="pers-info" action="/WorkingClass_war_exploded/invite" method="POST">
            <label class="txt">User:</label>
            <select name="target" id="">
                <option value="0">Choose from the list</option>
                <#list users as user>
                    <option value="${user.getId()}">${user.getFirstName() + " " + user.getSecondName()}</option>
                </#list>
            </select>
            <label class="txt">Position:</label>
            <input name="post" placeholder="Enter Position">
            <label class="txt">Salary:</label>
            <input type ="Number" class="name" name="value" placeholder="Enter value">
            <button class="submit-button" type="submit">Save</button>
        </form>
    </div>
</#macro>


<@display_page />