<html>
<body>
<form action="/WorkingClass_war_exploded/user">
    <button type="submit">Back</button>
</form>
<form action="/WorkingClass_war_exploded/create_company" method="POST" enctype="multipart/form-data">
    <div>Name:</div>
    <input type="text" name="name"/>
    <div>Info:</div>
    <input type="text" name="info"/>
    <div>Photo:</div>
    <input type="file" name="photo"/>
    <input type="submit" name="submit"/>
</form>
</body>
</html>