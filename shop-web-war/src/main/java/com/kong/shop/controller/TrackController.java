/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  TrackController.java 2016-01-18 15:55:26 $
 */
package com.kong.shop.controller;

import com.kong.shop.domain.Track;
import com.kong.shop.service.impl.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = "/track")
public class TrackController {

    @Autowired
    TrackServiceImpl trackService;

    @ResponseBody
    @RequestMapping(value = "/addTrack.do")
    public HashMap<String, Object> addTrack(HttpServletRequest request, HttpServletResponse response, Track track) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = trackService.add(track);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTrack.do")
    public HashMap<String, Object> deleteTrack(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        int result = trackService.delete(id);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyTrack.do")
    public HashMap<String, Object> modifyTrack(HttpServletRequest request, HttpServletResponse response, Track track) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        int flag = trackService.update(track);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getTrack.do")
    public HashMap<String, Object> getTrack(HttpServletRequest request, HttpServletResponse response, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Track track = trackService.fetch(id);
        map.put("track", track);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryTrackPage.do")
    public Map<String, Object> queryTrackPage(HttpServletRequest request, HttpServletResponse response, String pageSize, String currentPage, Track track) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Track> trackList = new ArrayList<Track>();
        Map<String, Object> condition = new HashMap<String, Object>();

//        if (track != null) {
//            if (track.getName() != null && track.getName() != "") {
//                SearchField searchField = new SearchField();
//                searchField.setField("name");
//                searchField.setOp("like");
//                searchField.setData("%" + track.getName() + "%");
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
            trackList = trackService.queryPage(condition, current * size, size);
            map.put("trackList", trackList);
            map.put("currentPage", current);
            map.put("totalRecord", trackService.count(condition));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
