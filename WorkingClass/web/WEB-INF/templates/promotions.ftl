<html>
    <body>
        <#list requests as elem>
            <div>
                <div>Sender: ${elem.getSender().getFirstName()} ${elem.getSender().getSecondName()}</div>
                <div>Message: ${elem.getMessage()}</div>
                <span>
                    <form method="post" action="/WorkingClass_war_exploded/create_new_factor">
                        <button type="submit" name="id" value="${elem.getId()}">Yes</button>
                    </form>
                </span>
                <span>
                    <form method="post" action="/WorkingClass_war_exploded/promotions">
                        <button type="submit" name="id" value="${elem.getId()}">No</button>
                    </form>
                </span>
            </div>
            <br/>
        </#list>
    </body>
</html>