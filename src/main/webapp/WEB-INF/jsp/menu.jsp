<ul class="nav nav-pills">
    <li class="nav-item">
        <a class="nav-link" href="add-user.html"><s:message code="index.addUserLink"/></a>
    </li>
    <sec:authorize access="hasRole('ADMIN')">
        <li class="nav-item">
            <a class="nav-link" href="show-users.html"><s:message code="index.showUsersLink"/></a>
        </li>
    </sec:authorize>
    <li class="nav-item">
        <a class="nav-link" href="logout.html">
            <s:message code="index.logoutLink"/>
            <sec:authentication property="principal.username"/>
        </a>
    </li>
</ul>