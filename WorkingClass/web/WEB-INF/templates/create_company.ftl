<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/company.css" />
    <script src="js/company.js"></script>
</#macro>
<#macro info>
    <div class="pers-info">
        <form class="pers-info" action="/WorkingClass_war_exploded/create_company" method="POST" enctype="multipart/form-data">
            <div style="color:red">${error!}</div>
            <label class="txt" for="first-name">Name:</label>
            <input class="name" placeholder="Enter company name" name="name"/>
            <label class="txt" for="work-info">Info:</label>
            <textarea class="info-comp" placeholder="Enter Information about company" name="info"></textarea>
            <div class="form-group">
                <label class="txt" for="my-input">Select image:</label>
                <input id="my-input" accept="image/jpeg,image/png" class="form-control-file" type="file" name="photo">
            </div>
            <button class="submit-button" name="submit" type="submit">Save</button>
        </form>
    </div>
</#macro>

<@display_page />