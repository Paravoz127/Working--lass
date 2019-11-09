<#macro css_js>
    <link rel="stylesheet" href="css/chat.css" />
    <script src="js/chat.js"></script>
    <script type="text/javascript">
        window.onload = function() {
            var text = document.getElementById("text");
            text.scrollTop = text.scrollHeight;
        }
    </script>
</#macro>

<#macro info action >
    <div class="messages-container"  id="text">
        <#list messages as msg>
            <#if msg.getSender().equals(user)>
                <div class="right-mes mes">
                    <div><label class="txt">${msg.getSender().toString()}:</label></div>
                    <#list msg.getImages() as image>
                        <#if image.getImagePath() != "null">
                            <div class="image-container"><img class=image src="${image.getImagePath()}"></div>
                        </#if>
                    </#list>
                    <div> <label class="mes">${msg.getText()}</label></div>
                </div>
            <#else>
                <div class="left-mes mes">
                    <div>
                        <label class="txt">${msg.getSender().toString()}:</label>
                    </div>
                    <#list msg.getImages() as image>
                        <#if image.getImagePath() != "null">
                            <div class="image-container"><img class=image src="${image.getImagePath()}"></div>
                        </#if>
                    </#list>
                    <div>
                        <label class="mes">${msg.getText()}</label>
                    </div>
                </div>
            </#if>
        </#list>

    </div>
    <div class="pers-info">
        <form  action="${action}" class="pers-info" method="POST" enctype="multipart/form-data">
            <div style="color:red">${error!}</div>
            <textarea class="message" name="message" placeholder="Enter Message"></textarea>
            <div class="buttons">
                <button class="submit-button" type="submit" name="id" value="${receiver.getId()}">Send</button>
                <div class="form-group">
                    <label for="my-input">Send File</label>
                    <input id="my-input" class="form-control-file" type="file" name="images" accept="image/jpeg,image/png" multiple>
                </div>
            </div>
        </form>
    </div>
</#macro>