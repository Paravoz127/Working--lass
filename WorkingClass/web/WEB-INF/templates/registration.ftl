<html>
<body>
<form action="/WorkingClass_war_exploded/main">
    <button type="submit">Back</button>
</form>
<form action="/WorkingClass_war_exploded/registration" method="POST">
    <div>First name:</div>
    <input type="text" name="first_name"/>
    <div>Second name:</div>
    <input type="text" name="second_name"/>
    <div>Date of birthday:</div>
    <input type="date" name="date"/>
    <div>Email:</div>
    <input type="text" name="email"/>
    <div>Password:</div>
    <input type="password" name="password"/>
    <div>Password again:</div>
    <input type="password" name="password2"/>
    <div>Remember me:</div>
    <input type="checkbox" name="safeMe" value="true">
    <input type="submit">
</form>
</body>
</html>