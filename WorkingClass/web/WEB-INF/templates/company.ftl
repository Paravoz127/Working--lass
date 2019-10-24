<html>
<body>
<form action="/WorkingClass_war_exploded/user">
    <button type="submit">Back</button>
</form>

    <div>Name:</div>
    <div>${company.getName()}</div>
    <div>Info:</div>
    <div>${company.getInfo()}</div>
    <div>Photo:</div>
    <#if company.getImage() ??>
        <div> <img src="${path!}"> </div>
    <#else>
        <div> <img src="def"> </div>
    </#if>

</body>
</html>