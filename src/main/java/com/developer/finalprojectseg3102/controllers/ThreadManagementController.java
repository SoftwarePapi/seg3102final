package com.developer.finalprojectseg3102.controllers;

import com.developer.finalprojectseg3102.dao.CommentDAO;
import com.developer.finalprojectseg3102.dao.ThreadDAO;
import com.developer.finalprojectseg3102.dao.UserDAO;
import com.developer.finalprojectseg3102.models.Comment;
import com.developer.finalprojectseg3102.models.Thread;
import com.developer.finalprojectseg3102.models.User;

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
    	if (isLoggedIn(session)) {
    		Thread thread = ThreadDAO.retrieve(Long.valueOf(2));
            Comment comment = CommentDAO.retrieve(Long.valueOf(1));
            
            User commentAuthor = UserDAO.retrieve(comment.getAuthor_id());
            model.addAttribute("commentAuthor", commentAuthor);
            model.addAttribute("thread", thread);
            model.addAttribute("comment", comment);
            return "thread";
    	}
    	else {
    		return "login";
    	}
    }

}
