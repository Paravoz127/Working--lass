<#import "meta_info.ftl" as top_info>
<#macro css_js></#macro>
<#macro info></#macro>

<#macro display_page>
<@top_info.meta_inf/>
        <link
                rel="stylesheet"
                href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
                integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
                crossorigin="anonymous"
        />
        <@css_js />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script
                src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"
        ></script>
        <script
                src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"
        ></script>
        <title>Profile</title>
    </head>
    <body>
    <nav class="nav-panel">
        <form action="/WorkingClass_war_exploded/user" method="POST">
            <a class="logo" href="/WorkingClass_war_exploded/user">Profile</a>
            <a href="/WorkingClass_war_exploded/dialogs">Messages</a>
            <#if user.getCompany() ??>
                <a href="/WorkingClass_war_exploded/promotions">Promotions</a>
                <a href="/WorkingClass_war_exploded/company?id=${user.getCompany().getId()}">Company</a>
            <#else>
                <span style="margin-right:15%;"></span>
            </#if>
            <button type="submit" class="signoff" name="logout" value="true">Sign Off</button>

        </form>
    </nav>
    <div class="wrapper">
        <div class="info">
            <@info/>
        </div>
        <div class="back"></div>
    </body>
    </html>
</#macro>