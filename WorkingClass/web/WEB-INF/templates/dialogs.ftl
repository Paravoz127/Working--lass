<html>
    <body>
        <#if user.getCompany ??>
            <form action="/WorkingClass_war_exploded/com_messages" method="GET">
                <label>${user.getCompany().getName()}:</label>
                <button type="submit">Company messages</button>
            </form>
        </#if>

        <#list users as u>
            <div>
                <form action="/WorkingClass_war_exploded/messages" method="GET">
                    <label>${u.getFirstName()} ${u.getSecondName()}:</label>
                    <button type="submit" name="id" value="${u.getId()}">To messages</button>
                </form>
            </div>
        </#list>
    </body>
</html>