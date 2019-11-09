<#import "factor_of_salary.ftl" as factor/>
<#include "authenticated.ftl" />

<#macro info>
    <@factor.info submit_info="name=\"user_id\" value=\"${user.getId()}\"" form_action="/WorkingClass_war_exploded/create_factor"/>
</#macro>

<#macro css_js>
    <@factor.css_js/>
</#macro>

<@display_page />