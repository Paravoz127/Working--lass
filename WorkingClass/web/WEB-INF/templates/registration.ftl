<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="css/reg.css" />
    <script src="js/reg.js"></script>
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
</div>
</body>
</html>
