package com.developer.finalprojectseg3102.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by Parastoo on 11/29/2019.
 */
@Controller
public class ThreadManagementController extends BaseController {

    @RequestMapping(value="/thread")
    public String getThreadPage(Model model, HttpSession session) {
        return "thread";
    }

}
