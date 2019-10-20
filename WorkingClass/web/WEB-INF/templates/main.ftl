<html>
    <body>
        <form action="/WorkingClass_war_exploded/registration">
            <button type="submit">Registration</button>
        </form>
        <#if login_error ??>
            <div style="color: red">wrong login or password</div>
        <#else>
        </#if>
        <form action="/WorkingClass_war_exploded/main" method="POST">
            <div>Email:</div>
            <input type="text" name="email"/>
            <div>Password:</div>
            <input type="password" name="password"/>
            <div>Remember me:</div>
            <input type="checkbox" name="safeMe" value="true">
            <input type="submit">
        </form>
    </body>
</html>
