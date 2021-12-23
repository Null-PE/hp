package com.hr.hr_common.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    public HttpServletRequest request;
    public HttpServletResponse response;

    protected String companyId;
    protected String companyName;
    protected String userId;

    @ModelAttribute
    public void serResAndReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        if (principalCollection != null && !principalCollection.isEmpty()) {

        }

    }
}
