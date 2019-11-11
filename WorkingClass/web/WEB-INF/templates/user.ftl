<#include "authenticated.ftl" />

<#macro css_js>
    <link rel="stylesheet" href="css/profile.css" />
    <script src="js/profile.js"></script>
</#macro>

<#macro info>
    <div class="img">
        <div class="foto">
            <#if user.getImage() ??>
                <img class="image" src="${user.getImage().getImagePath()}" />
            <#else>
                <img class="image" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"/>
            </#if>
        </div>
        <#if show_info >
            <form action="/WorkingClass_war_exploded/create_factor" method="GET">
                <button class="edit" type="submit">create new factor</button>
            </form>
        <#else>
            <#if is_employee>
                <form action="/WorkingClass_war_exploded/create_factor" method="GET">
                <button type="submit" class="edit" name="userId" value="${user.getId()}">change salary</button>
                </form>
            </#if>
            <form action="/WorkingClass_war_exploded/messages" method="GET">
                <button class="edit" type="submit" name="id" value="#{user.getId()}">message</button>
            </form>
        </#if>
    </div>
    <div class="pers-info">
        <label class="txt" for="first-name">First Name:</label>
        <label class="f-name" for="f-name">${user.getFirstName()}</label>
        <label class="txt" for="second-name">Second Name:</label>
        <label class="s-name" for="s-name">${user.getSecondName()}</label><br>
        <label class="txt" for="second-name">Date of birthday:</label>
        <label class="s-name" for="s-name">${user.getDate().toString()}</label><br>
        <label class="txt" for="work-info">Work info</label>
        <#if user.getCompany() ??>
            <label class="txt" for="company"
            >Company: <a href="/WorkingClass_war_exploded/company?id=${user.getCompany().getId()}">${user.getCompany().getName()}</a></label
            >
            <#if user.getBoss() ??>
                <label class="txt" for="Boss"
                >Boss: <a href="/WorkingClass_war_exploded/user?id=${boss.getId()}"> ${boss.getFirstName()} ${boss.getSecondName()} </a></label
                >
            </#if>
            <#if show_info >
                <#if user.getBoss() ??>
                    <label class="txt">Salary:<label class="salary">${positive_sum + negative_sum}$</label></label>
                </#if>
                <a href="/WorkingClass_war_exploded/invite">Invite user to company</a><br>
            </#if>
            <label class="txt" for="employees">Employees:</label>
            <div class="employees-list">
                <ul class="list">
                    <#list employees as emp>
                        <li class="item"><a href="/WorkingClass_war_exploded/user?id=${emp.getId()}">${emp.getFirstName()} ${emp.getSecondName()}</a></li>
                    <#else>
                        <div>You haven`t employees</div>
                    </#list>
                </ul>
            </div>
        <#else>
            <#if show_info >
                <label class="txt">You have no job</label>
                <form action="/WorkingClass_war_exploded/create_company" method="GET">
                    <button class="company-btn" type="submit">Create company</button>
                </form>
            <#else>
                <label class="txt">User has no job</label>
            </#if>

        </#if>
    </div>
    <#if show_info>
        <div class="salfacts-invites">
            <ul class="nav nav-tabs nav-fill" id="myTab" role="tablist">
                <li class="nav-item">
        <a class="nav-link active" id="invites-tab" data-toggle="tab" href="#invites" role="tab" aria-controls="invites" aria-selected="false">Invites</a>
    </li>
                <#if user.getCompany()?? && user.getBoss() ??>
                    <li class="nav-item">
                        <a class="nav-link" id="sal-factors-tab" data-toggle="tab" href="#sal-factors" role="tab" aria-controls="sal-factors" aria-selected="true">Salary factors</a>
                    </li>
                </#if>
            </ul>
            <div class="tab-content" id="myTabContent">
            <#if user.getCompany() ??>
                <div class="tab-pane fade show active" id="invites" role="tabpanel" aria-labelledby="invites-tab">
                    <div class="list-container">
                        <div class="list-group list-group-flush">
                            <#list invites_from as invite>
                                <li class="list-group-item">
                                    <label class="sal-factor-item">
                                        <label class="txt">User:<a href="/WorkingClass_war_exploded/user?id=${invite.getFactorAndUser().getUser().getId()}">${invite.getFactorAndUser().getUser().toString()}</a></label>
                                        <label class="txt">Position:<label class="nf">${invite.getFactorAndUser().getFactorOfSalary().getName()}</label></label>
                                        <label class="txt">Money:<label class="nf">${invite.getFactorAndUser().getValue()}$</label></label>
                                    </label>
                                </li>
                            <#else>
                                <div style="text-align:center"><label class="txt">You have no invites</label></div>
                            </#list>
                        </div>
                    </div>
                </div>
            <#else>
                <div class="tab-pane fade show active" id="invites" role="tabpanel" aria-labelledby="invites-tab">
                    <div class="list-container">
                        <div class="list-group list-group-flush">
                            </li>
                            <#list invites as invite>
                                <li class="list-group-item">
                                    <label class="sal-factor-item">
                                        <label class="txt">Company:<a href="/WorkingClass_war_exploded/company?id=${invite.getSender().getCompany().getId()}">${invite.getFactorAndUser().getFactorOfSalary().getCompany().getName()}</a></label>
                                        <label class="txt">Position:<label class="nf">${invite.getFactorAndUser().getFactorOfSalary().getName()}</label></label>
                                        <label class="txt">Salary:<label class="nf">${invite.getFactorAndUser().getValue()}$</label></label>
                                        <label class="pos-buttons">
                                            <form method="post" class="form" action="/WorkingClass_war_exploded/agree_to_invite">
                                                <button class="accept" type=submit name="true" value="${invite.getId()}">Accept</button>
                                            </form>
                                            <form method="post" class="form" action="/WorkingClass_war_exploded/agree_to_invite">
                                                <button class="reject" type="submit" name="false" value="${invite.getId()}">Reject</button>
                                            </form>
                                        </label>
                                    </label>
                                </li>
                            <#else>
                                <div style="text-align:center"><label class="txt">You have no invites</label></div>
                            </#list>
                        </div>
                    </div>
                </div>
            </#if>
            <#if user.getBoss()?? && user.getCompany??>
                <div class="tab-pane fade" id="sal-factors" role="tabpanel" aria-labelledby="sal-factors-tab"><div class="list-group list-group-flush">
                        <div class="list-container">
                            <a data-toggle="modal" data-target="#exampleModal" class="list-group-item list-group-item-action" id="req">New Request</a>
                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">New Request</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form class="pers-info" action="/WorkingClass_war_exploded/promotion" method="POST">
                                                <div class="body-container">

                                                    <label class="txt">Message:</label>
                                                    <textarea class="message" placeholder="Enter Message" name="message"></textarea>
                                                    <button type="submit" class="btn btn-secondary">Send</button>
                                            </form>
                                        </div>
                                        <div class="form-group">
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div></div>
                        <#list positive as factor>
                            <li class="list-group-item list-group-item-success">
                            <label class="sal-factor-item">
                            <label class="txt">Name:<label class="nf">${factor.getFactorOfSalary().getName()}</label></label>
                            <label class="txt">Value:<label class="nf">${factor.getValue()}$</label></label>
                            <label class="txt">Info:<br><label class="nf">${factor.getFactorOfSalary().getInfo()}</label></label>
                            </label>
                            </li>
                        </#list>
                        <#list negative as factor>
                            <li class="list-group-item list-group-item-danger">
                            <label class="sal-factor-item">
                            <label class="txt">Name:<label class="nf">${factor.getFactorOfSalary().getName()}</label></label>
                            <label class="txt">Value:<label class="nf">${factor.getValue()}$</label></label>
                            <label class="txt">Info:<br><label class="nf">${factor.getFactorOfSalary().getInfo()}</label></label>
                            </label>
                            </li>
                        </#list>
                    </div>
            </#if>
        </div>
    </#if>
    </div>
</#macro>

<@display_page />