<html>
<body>
<form action="/WorkingClass_war_exploded/promotions">
    <button type="submit">Back</button>
</form>
<form action="/WorkingClass_war_exploded/factor_by_promotion" method="POST">
    </select>
    <div>Name of factor:</div>
    <input type="text" name="factor_name"/>
    <div>Message:</div>
    <input type="text" name="message" value="${promotion.getMessage()}">
    <div>Value:</div>
    <input type="number" name="value"/>
    <input type="submit" name="promotion" value="${promotion.getId()}"/>
</form>
</body>
</html>