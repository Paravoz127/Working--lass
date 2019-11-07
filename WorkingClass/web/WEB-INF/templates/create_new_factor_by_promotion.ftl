<#include "factor_of_salary.ftl" />

<#macro message_value>${promotion.getMessage()}</#macro>
<#macro submit_info>name="promotion" value="${promotion.getId()}"</#macro>
<#macro form_action>/WorkingClass_war_exploded/factor_by_promotion</#macro>

<@display_page />