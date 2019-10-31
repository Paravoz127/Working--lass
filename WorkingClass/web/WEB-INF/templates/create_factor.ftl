<html>
<body>
<form action="/WorkingClass_war_exploded/promotions">
    <button type="submit">Back</button>
</form>
<form action="/WorkingClass_war_exploded/create_factor" method="POST">
    </select>
    <div>Name of factor:</div>
    <input type="text" name="factor_name"/>
    <div>Message:</div>
    <input type="text" name="message">
    <div>Value:</div>
    <input type="number" name="value"/>
    <input type="submit" name="user_id" value="${user.getId()}"/>
</form>
</body>
</html>