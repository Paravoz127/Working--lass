<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/company.css" />
    <script src="js/company.js"></script>
</#macro>
<#macro info>
    <div class="img">
        <div class="foto">
            <img class="image" src="${path!}"/>
        </div>
    </div>
    <div class="pers-info">
        <label class="txt" for="first-name">Name:</label>
        <label class="name" for="f-name">${company.getName()}</label>
        <label class="txt" for="work-info">Info:</label>
        <label class="info-comp">${company.getInfo()}</label>
    </div>
</#macro>

<@display_page />