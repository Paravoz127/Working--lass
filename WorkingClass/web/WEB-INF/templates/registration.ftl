<#include "not_auth.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/reg.css" />
    <script src="js/reg.js"></script>
</#macro>
<#macro form>
    <form
            action="/WorkingClass_war_exploded/registration" method="POST"
            id="reg-id"
            onsubmit="javascript:return validate('reg-id', 'email')"
    >
        <div class="container">
            <label for="first-name">First Name</label>
            <input type="text" name="first_name" placeholder="Enter First Name" id="fname" required />
            <label for="second-name">Second Name</label>
            <input type="text" name="second_name" placeholder="Enter Second Name" id="sname" required />
            <label for="email">Email</label>
            <input type="text" name="email" placeholder="Enter Email" id="email" required />
            <label for="bdate">Birth Date</label>
            <input type="date" name="date" id="bdate" required />
            <label for="password">Password</label>
            <input
                    name="password"
                    type="password"
                    placeholder="Enter Password"
                    name="password"
                    required
            />
            <label for="confirm-password">Confirm Password</label>
            <input
                    name="password2"
                    type="password"
                    placeholder="Confirm Password"
                    name="confirm-password"
                    required
            />
            <button type="submit" class="button-login">Sign Up</button>
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