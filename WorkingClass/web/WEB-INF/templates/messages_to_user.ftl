<#list messages as msg>
    <div>${msg.getSender().getFirstName()}:</div>
    <#list msg.getImages() as image>
        <img src="${image.getImagePath()}">
    </#list>
    <div>${msg.getText()}</div>
</#list>

<form action="/WorkingClass_war_exploded/messages" method="POST" enctype="multipart/form-data">
    <input name="message"/>
    <input type="file" name="photo1"/>
    <input type="file" name="photo2"/>
    <input type="file" name="photo3"/>
    <input type="file" name="photo4"/>
    <input type="file" name="photo5"/>
    <button type="submit" name="id" value="${receiver.getId()}" >Send</button>
</form>