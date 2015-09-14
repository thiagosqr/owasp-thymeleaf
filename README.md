# owasp-thymeleaf

##OWASP CSRFGuard 3 integration with Thymeleaf
 OWASP CSRFGuard 3 does not support Thymeleaf so an integration had to be made using [CsrfLink](https://github.com/thiagosqr/app-seed/blob/master/app-web/src/main/java/com/github/thiagosqr/conf/security/CsrfLink.java) class. 
 
 Eg. 
 ```html
    <a href="#" csrf:token_for="data-href" th:attr="data-href=${it.id+'/delete'}" data-th-text="#{delete.button.label}" class="btn btn-outline btn-danger" data-toggle="modal" data-target="#confirm-delete">delete</a>
 ```
