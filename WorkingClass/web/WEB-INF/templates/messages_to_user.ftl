<#import "messages.ftl" as mes/>
<#include "authenticated.ftl" />

<#macro info>
    <@mes.info action="messages"/>
</#macro>

<#macro css_js>
    <@mes.css_js/>
</#macro>

<@display_page />