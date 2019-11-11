<#import "factor_of_salary.ftl" as factor/>
<#include "authenticated.ftl" />


<#macro info>
    <@factor.info submit_info="name=\"promotion\" value=\"${promotion.getId()}\"" form_action="/WorkingClass_war_exploded/factor_by_promotion"/>
</#macro>

<#macro css_js>
    <@factor.css_js message_value="${message}"/>
</#macro>

<@display_page />