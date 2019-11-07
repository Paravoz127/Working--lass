<#include "not_auth.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/main.css" />
    <script src="js/main.js"></script>
</#macro>
<#macro form>
    <form id="login-id" onsubmit="javascript:return validate('login-id', 'email')" action="/WorkingClass_war_exploded/main" method="POST">
    <div class="container">
        <label for="email"><b>Email</b></label>
        <input type="text" name="email" placeholder="Enter Email" id="email" required />

        <label for="password"><b>Password</b></label>
        <input
                type="password"
                placeholder="Enter Password"
                name="password"
                required
        />

        <button type="submit" class="button-login">login</button>
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