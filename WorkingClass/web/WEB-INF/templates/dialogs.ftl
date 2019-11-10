<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/messages.css" xmlns="http://www.w3.org/1999/html"/>
    <script src="js/messages.js"></script>
</#macro>
<#macro info>
    <div class="list-container">
        <div class="list-group list-group-flush">
            <#if user.getCompany() ??>
                <a href="/WorkingClass_war_exploded/com_messages" class="list-group-item list-group-item-action">
                    <label class="sal-factor-item">
                        <label class="txt"><img src="${user.getCompany().getImage().getImagePath()}" class="profile-image">  ${user.getCompany().getName()}</label>
                    </label>
                </a>
            </#if>
            <#list users as u>
                <a href="/WorkingClass_war_exploded/messages?id=${u.getId()}" class="list-group-item list-group-item-action">
                    <label class="sal-factor-item">
                        <#if u.getImage()??>
                            <label class="txt"><img src="${u.getImage().getImagePath()}" class="profile-image">  ${u.toString()}</label>
                        <#else>
                            <label class="txt"><img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png" class="profile-image">  ${u.toString()}</label>
                        </#if>

                    </label>
                </a>
            </#list>
    <br/>
    <div class="dropdown dropright search">
        <label class="txt">New Dialog</label>
        <input autocomplete="off" id="dropdown" type="text" oninput="ajaxSearch()" onclick="ajaxSearch()" name="users" id="" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></input>
        <div id="dropdown-menu" class="dropdown-menu menu">
            <div class="filters">
                <label class="txt">Filters: </label>
                <div>
                    <input type="radio" id="-1" oninput="ajaxSearch()" onclick="ajaxSearch()" name="filter" checked="checked">All</input>
                    <input type="radio" id="0" oninput="ajaxSearch()" onclick="ajaxSearch()" name="filter"/>Without job</input>
                    <input type="radio" id="1" oninput="ajaxSearch()" onclick="ajaxSearch()" name="filter"/>With job</input>
                </div>
                <br/>
            </div>
            <div id="container"></div>
        </div>
    </div>
        </div>

    </div>
</#macro>

<@display_page />