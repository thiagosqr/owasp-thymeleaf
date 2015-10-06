# owasp-thymeleaf

##OWASP CSRFGuard 3 integration with Thymeleaf
 OWASP CSRFGuard 3 does not support Thymeleaf so an integration had to be made using [CsrfLink](https://github.com/thiagosqr/app-seed/blob/master/app-web/src/main/java/com/github/thiagosqr/conf/security/CsrfLink.java) class. 
 
 
 ##Maven Dependency
 ```xml
 <dependency>
     <groupId>com.github.thiagosqr</groupId>
     <artifactId>owasp-thymeleaf</artifactId>
     <version>1.0.0</version>
 </dependency>
 ```
 
 ##Example
 
 Eg. 
 ```html

    <html xmlns:th="http://www.thymeleaf.org" xmlns:csrf="http://www.w3.org/1999/xhtml">
    ...     
    <a href="#" csrf:token_for="data-href" th:attr="data-href=${it.id+'/delete'}" data-th-text="#{delete.button.label}" class="btn btn-outline btn-danger" data-toggle="modal" data-target="#confirm-delete">delete</a>
 ```
