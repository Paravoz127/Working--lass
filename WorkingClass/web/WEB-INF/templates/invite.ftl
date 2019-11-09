<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/invite.css" />
    <script src="js/invite.js"></script>
</#macro>

<#macro info>
    <div class="pers-info">
        <form class="pers-info" action="/WorkingClass_war_exploded/invite" method="POST">
            <label class="txt">User:</label>
            <div class="dropdown">
                <input id="dropdown" type="text" oninput="dropValueWithSearch()" onclick="ajaxSearch()" name="users" id="" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></input>
                <div id="dropdown-menu" class="dropdown-menu">
                    <#list users as user>
                        <div id="${user.getId()}" onclick="onDropDown(this)" class="dropdown-item" href="">
                            <#if user.getImage() ??>
                                <img class="small-image" src="${user.getImage().getImagePath()}"/>
                            <#else>
                                <img class="small-image" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"/>
                            </#if>
                            ${user.toString()}
                        </div>
                    </#list>
                </div>
            </div>
            <label class="txt">Position:</label>
            <input name="post" placeholder="Enter Position">
            <label class="txt">Salary:</label>
            <input type ="Number" class="name" name="value" placeholder="Enter value">
            <button class="submit-button" name="target" id="submit" type="submit">Save</button>
        </form>
    </div>
</#macro>


<@display_page />