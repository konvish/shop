/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  CommentController.java 2016-01-18 15:55:23 $
 */
package com.kong.shop.controller;

import com.kong.shop.domain.Comment;
import com.kong.shop.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/10.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentController extends BaseController {

    @Autowired
    CommentServiceImpl commentService;

    @Override
    public String getTemplateRoot() {
        return "/html/comment";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(this.getTemplateRoot() + "/index", map);
    }

    @ResponseBody
    @RequestMapping(value = "/addComment.do")
    public HashMap<String, Object> addComment(HttpServletRequest request, HttpServletResponse response, Comment comment) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = commentService.add(comment);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteComment.do")
    public HashMap<String, Object> deleteComment(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = commentService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyComment.do")
    public HashMap<String, Object> modifyComment(HttpServletRequest request, HttpServletResponse response, Comment comment) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = commentService.update(comment);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getComment.do")
    public HashMap<String, Object> getComment(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Comment comment = commentService.fetch(id);
        map.put("comment", comment);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryCommentPage.do")
    public Map<String, Object> queryCommentPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Comment comment) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Comment> commentList = new ArrayList<Comment>();
        Map<String, Object> condition = new HashMap<String, Object>();

//        if (comment != null) {
//            if (comment.getName() != null && comment.getName() != "") {
//                SearchField searchField = new SearchField();
//                searchField.setField("name");
//                searchField.setOp("like");
//                searchField.setData("%" + comment.getName() + "%");
//                condition.put("name", searchField);
//            }
//        }

        int current = 0;
        int size = 10;
        if (pageSize != null || currentPage != null) {
            current = Integer.parseInt(currentPage) - 1;
            size = Integer.parseInt(pageSize);
        }
        try {
            commentList = commentService.queryPage(condition, current * size, size);
            map.put("commentList", commentList);
            map.put("currentPage", current);
            map.put("totalRecord", commentService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
