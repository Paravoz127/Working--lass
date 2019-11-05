<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="css/main.css" />
    <script src="js/main.js"></script>
    <title>Document</title>
</head>
<body>
<div class="logo-container">
    Employee List
</div>
<div class="wrapper">
    <div class="desc-container">
        <h1 class="head">
            About us
        </h1>
        <p class="text-about">
            Website for accounting salaries of employees. An employee can apply for a salary increase, see what allowances for what he receives. The employer can tie workers to himself, appoint and edit his salary.
        </p>
    </div>
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
</div>
</body>
</html>