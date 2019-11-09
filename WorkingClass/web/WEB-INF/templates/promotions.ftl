<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/promotions.css" xmlns="http://www.w3.org/1999/html"/>
    <script src="js/promotions.js"></script>
</#macro>
<#macro info>
    <div class="list-container">
        <div class="list-group list-group-flush">
            <#list requests as elem>
                <li class="list-group-item">
                    <label class="sal-factor-item">
                        <label class="txt">Sender:<a href="/WorkingClass_war_exploded/user?id=${elem.getSender().getId()}">${elem.getSender().toString()}</a></label>
                        <label class="txt">Message:<label class="nf">${elem.getMessage()}</label></label>
                        <label class="pos-buttons">
                            <form method="get" class="form" action="/WorkingClass_war_exploded/factor_by_promotion">
                                <button class="accept" type="submit" name="id" value="${elem.getId()}">Accept</button>
                            </form>
                            <form method="post" class="form" action="/WorkingClass_war_exploded/promotions">
                                <button class="reject" type="submit" name="id" value="${elem.getId()}">Reject</button></label>
                            </form>
                    </label>
                </li>
            <#else>
                <label style="font-size: 45px" class="txt">No requests found</label>
            </#list>
        </div>
    </div>
</#macro>

<@display_page />