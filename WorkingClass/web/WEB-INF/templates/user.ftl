<html>
    <body>
        <form action="/WorkingClass_war_exploded/user" method="POST">
            <button type="submit" name="logout" value="true">Logout</button>
        </form>
        <#if user.getImage() ??>
            <div> <img src="${user.getImage().getImagePath()}"> </div>
        <#else>
            <div> <img src="def"> </div>
        </#if>
        <div>
            <div>${user.getFirstName()} ${user.getSecondName()}</div>
        </div>
        <#if show_info && show_info == true ??>
            <form action="/WorkingClass_war_exploded/set" method="GET">
                <button type="submit">edit</button>
            </form>
            <form action="/WorkingClass_war_exploded/dialogs" method="GET">
                <button type="submit">my messages</button>
            </form>
        <#else>
            <form action="/WorkingClass_war_exploded/messages" method="GET">
                <button type="submit" name="id" value="#{user.getId()}">Message</button>
            </form>
        </#if>
        <#if user.getCompany() ??>
            <div>${user.getCompany().getName()}</div>
            <#if boss  ??>
                <div>Boss: <a href="/WorkingClass_war_exploded/user?id=${boss.getId()}"> ${boss.getFirstName()} ${boss.getSecondName()} </a></div>
            </#if>
            <form action="/WorkingClass_war_exploded/company">
                <button type="submit" name="id" value="${user.getCompany().getId()}">show company</button>
            </form>
            <#if show_info && show_info == true ??>
                <form action="/WorkingClass_war_exploded/com_messages">
                    <button type="submit">message to company chat</button>
                </form>
                <form action="/WorkingClass_war_exploded/invite">
                    <button type="submit">invite user</button>
                </form>
                <div>Your invites:</div>
                <#list invites_from as invite>
                    <br/>
                    <div>Target: ${invite.getFactorAndUser().getUser().getFirstName()} ${invite.getFactorAndUser().getUser().getSecondName()}</div>
                    <div>Post: ${invite.getFactorAndUser().getFactorOfSalary().getName()}</div>
                    <div>Money: ${invite.getFactorAndUser().getValue()}</div>
                </#list>
                <br/>
                <#if employees ??>
                    <form action="/WorkingClass_war_exploded/promotions">
                        <button type="submit">promotions</button>
                    </form>
                </#if>
                <br/>
                <#if boss ??>
                    <form action="/WorkingClass_war_exploded/promotion">
                        <button type="submit">promotion request</button>
                    </form>
                    <div>Factors of salary:</div>
                    <#list positive as factor>
                        <div>Name: ${factor.getFactorOfSalary().getName()}</div>
                        <div>Info: ${factor.getFactorOfSalary().getInfo()}</div>
                        <div>Value: ${factor.getValue()}</div>
                    </#list>
                    <#list negative as factor>
                        <div>Name: ${factor.getFactorOfSalary().getName()}</div>
                        <div>Info: ${factor.getFactorOfSalary().getInfo()}</div>
                        <div>Value: ${factor.getValue()}</div>
                    </#list>
                    <div>Sum of salary: ${positive_sum + negative_sum}</div>
                </#if>
                <br/>
                <br/>
                <div>Employees:</div>
                <#list employees as emp>
                    <a href="/WorkingClass_war_exploded/user?id=${emp.getId()}">${emp.getFirstName()} ${emp.getSecondName()}</a>
                </#list>
            <#elseif is_employee && is_employee == true ??>
                <form action="/WorkingClass_war_exploded/create_factor" method="GET">
                    <button type="submit" name="userId" value="${user.getId()}">change salary</button>
                </form>
            </#if>

        <#else>
            <#if show_info && show_info == true  ??>
                <#list invites as invite>
                    <br/>
                    <div>Company: ${invite.getFactorAndUser().getFactorOfSalary().getCompany().getName()}</div>
                    <div>Post: ${invite.getFactorAndUser().getFactorOfSalary().getName()}</div>
                    <div>Money: ${invite.getFactorAndUser().getValue()}</div>
                    <div>
                        <span>
                            <form method="post" action="/WorkingClass_war_exploded/agree_to_invite">
                                <button type="submit" name="true" value="${invite.getId()}">Yes</button>
                            </form>
                        </span>
                        <span>
                            <form method="post" action="/WorkingClass_war_exploded/agree_to_invite">
                                <button type="submit" name="false" value="${invite.getId()}">No</button>
                            </form>
                        </span>
                    </div>
                </#list>
                <br/>
                <form action="/WorkingClass_war_exploded/create_company">
                    <button type="submit">create company</button>
                </form>
            </#if>
        </#if>


    </body>
</html>