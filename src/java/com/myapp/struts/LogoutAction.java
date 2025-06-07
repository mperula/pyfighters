/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author empichi
 */

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import java.util.Map;

public class LogoutAction extends ActionSupport {

    @Override
    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.clear();
        return SUCCESS;
    }
}

