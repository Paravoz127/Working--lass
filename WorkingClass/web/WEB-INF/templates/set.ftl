<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/profile-reg.css" />
    <script src="js/profile-reg.js"></script>
</#macro>
<#macro info>
    <div class="img">
        <div class="foto">
            <#if user.getImage() ??>
                <img class="image" src="${user.getImage().getImagePath()}" />
            <#else>
                <img class="image" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"/>
            </#if>
        </div>
    </div>
    <div class="pers-info">
        <div style="color:red">${error!}</div>
        <form class="pers-info" action="/WorkingClass_war_exploded/set" method="POST" enctype="multipart/form-data">
            <label class="txt" for="first-name">First Name:</label>
            <input autocomplete="off" class="name" name="first_name" value="${user.getFirstName()}" placeholder="Enter First name">
            <label class="txt" for="second-name">Second Name:</label>
            <input autocomplete="off" class="name" name="second_name" value="${user.getSecondName()}" placeholder="Enter Second name">
            <div class="form-group">
                <label class="txt" for="my-input">Select image:</label>
                <input id="my-input" name="photo" accept="image/jpeg,image/png" class="form-control-file" type="file" name="">
            </div>
            <button class="submit-button"  type="submit">Save</button>
        </form>
    </div>
</#macro>

<@display_page />