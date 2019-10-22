<html>
<body>
<form action="/WorkingClass_war_exploded/user">
    <button type="submit">Back</button>
</form>
<form action="/WorkingClass_war_exploded/set" method="POST" enctype="multipart/form-data">
    <div>First Name:</div>
    <input type="text" name="first_name" value="${user.getFirstName()}"/>
    <div>Second Name:</div>
    <input type="text" name="second_name" value="${user.getSecondName()}"/>
    <div>Photo:</div>
    <input type="file" name="photo"/>
    <input type="submit" name="submit"/>
</form>
</body>
</html>