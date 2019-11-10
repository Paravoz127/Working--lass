<#include "not_auth.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/main.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/main.js"></script>
</#macro>
<#macro form>
    <form id="login-id" oninput="javascript: validate()" action="/WorkingClass_war_exploded/main" method="POST">
    <div class="container">
            <div style="color:red">${error!}</div>
        <label for="email"><b>Email</b> <label for="Email-error" class="email-error" id="email-error">Error</label></label>
        <input autocomplete="off" type="text" name="email" value="${email!}" placeholder="Enter Email" id="email" required />

        <label for="password"><b>Password</b><label for="Password-error" class="password-error">Error</label></label>
        <input
                type="password"
                placeholder="Enter Password"
                name="password"
                required
        />

        <button type="submit" id="button-login" class="button-login">login</button>
        <label>
            <input type="checkbox" name="safeMe" value="true" name="remember" /> Remember
            me
        </label>
        <h2>Or</h2>
        <label>
            <a href="/WorkingClass_war_exploded/registration"><button type="button" class="button-reg">registration</button> </a>
        </label>
    </div>
</form>
</#macro>
<@display_page />