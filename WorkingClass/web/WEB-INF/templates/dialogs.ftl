<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/messages.css" />
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
            <#else>
                <#if !user.getCompany()??>
                    <div style="text-align: center"><label class="txt">You have no messages</label></div>
                </#if>
            </#list>
        </div>
    </div>
</#macro>

<@display_page />