<html>
<body>
<form action="/WorkingClass_war_exploded/user">
    <button type="submit">Back</button>
</form>
<form action="/WorkingClass_war_exploded/invite" method="POST">
    <div>User:</div>
    <select name="target">
        <option value="0">Choose from the list</option>
        <#list users as user>
            <option value="${user.getId()}">${user.getFirstName() + " " + user.getSecondName()}</option>
        </#list>
    </select>
    <div>Post:</div>
    <input type="text" name="post"/>
    <div>Value:</div>
    <input type="number" name="value"/>
    <input type="submit" name="submit"/>
</form>
</body>
</html>