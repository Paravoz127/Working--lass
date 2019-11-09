<#import "meta_info.ftl" as top_info>
<#macro css_js></#macro>
<#macro form></#macro>

<#macro display_page>
    <@top_info.meta_inf/>
        <@css_js />
        <title>Document</title>
    </head>
    <body>
    <div class="logo-container">
        Employee List
    </div>
    <div class="wrapper">
        <div class="desc-container">
            <h1 class="head">
                About us
            </h1>
            <p class="text-about">
                Website for accounting salaries of employees. An employee can apply for a salary increase, see what allowances for what he receives. The employer can tie workers to himself, appoint and edit his salary.
            </p>
        </div>
        <@form />
    </div>
    </body>
    </html>
</#macro>