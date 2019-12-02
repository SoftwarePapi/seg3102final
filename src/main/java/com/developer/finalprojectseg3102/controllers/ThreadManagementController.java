package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.CommentDAO;
import com.developer.finalprojectseg3102.dao.ThreadDAO;
import com.developer.finalprojectseg3102.models.Comment;
import com.developer.finalprojectseg3102.models.Thread;
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
    public String getThreadPage(Model model, HttpSession session) throws Exception {
        Thread thread = ThreadDAO.retrieve(Long.valueOf(2));
        Comment comment = CommentDAO.retrieve(Long.valueOf(1));
        model.addAttribute("thread", thread);
        model.addAttribute("comment", comment);
        return "thread";
    }

}
