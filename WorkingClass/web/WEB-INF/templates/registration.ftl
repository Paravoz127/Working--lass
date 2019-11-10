<#include "not_auth.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/reg.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/reg.js"></script>
</#macro>
<#macro form>
    <form
            action="/WorkingClass_war_exploded/registration" method="POST"
            id="reg-id"
            oninput="javascript: validate()"
    >
        <div class="container">
            <div style="color:red">${error!}</div>
            <label for="first-name">First Name <label for="Name-error" class="name-error" id="fname-error">Error</label></label>
            <input autocomplete="off" type="text" name="first_name" value="${first_name!}" placeholder="Enter First Name" id="fname" required />
            <label for="second-name">Second Name <label for="name-error" class="name-error" id="sname-error">Error</label></label>
            <input autocomplete="off" type="text" name="second_name" value="${second_name!}" placeholder="Enter Second Name" id="sname" required />
            <label for="email">Email <label for="Email-error" class="email-error" id="email-error">Error</label></label>
            <input autocomplete="off" type="text" name="email" value="${email!}" placeholder="Enter Email" id="email" required />
            <label for="bdate">Birth Date <label for="Date-error" class="date-error" id="date-error">Error</label></label>
            <input type="date" name="date" id="bdate" required />
            <label for="password">Password <label for="password-error" class="password-error" id="password-error">Error</label></label>
            <input
                    name="password"
                    type="password"
                    placeholder="Enter Password"
                    name="password"
                    required
                    id="password"
            />
            <label for="confirm-password">Confirm Password <label for="password-error" class="password-error" id="password-error2">Error</label></label>
            <input
                    name="password2"
                    type="password"
                    placeholder="Confirm Password"
                    name="confirm-password"
                    required
                    id="password2"
            />
            <button type="submit" id="button-login" class="button-login">Sign Up</button>
            <label>
                <input type="checkbox" name="safeMe" value="true" name="remember" /> Remember
                me
            </label>
            <h2>Or</h2>
            <label>
                <a href="/WorkingClass_war_exploded/main"><button type="button" class="button-reg">Log In</button></a>
            </label>
        </div>
    </form>
</#macro>
<@display_page />