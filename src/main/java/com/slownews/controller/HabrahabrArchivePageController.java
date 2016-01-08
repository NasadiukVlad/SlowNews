package com.slownews.controller;

import com.slownews.dao.ArchiveJpaDao;
import com.slownews.dao.HabrahabrArchiveJpaDao;
import com.slownews.domain.HabrahabrNewsArchive;
import com.slownews.domain.NewsArchive;
import com.slownews.model.BBCNews;
import com.slownews.model.HabrahabrNews;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Влад on 03.01.2016.
 */
public class HabrahabrArchivePageController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();

        HabrahabrArchiveJpaDao habrahabrNewsArchiveJpaDao = new HabrahabrArchiveJpaDao();

        List<HabrahabrNewsArchive> news = habrahabrNewsArchiveJpaDao.getAll();

        ArrayList archive = new ArrayList();

        for (int i = 0; i < news.size(); i++) {
            HashMap items = new HashMap();
            items.put("title", news.get(i).getTitle());
            items.put("description", news.get(i).getDescription());
            items.put("link", news.get(i).getLink());
            archive.add(items);
        }

        context.setAttribute("habrahabrArchiveNewsList", archive);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/HabrahabrArchive.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Map<String, String[]> parametersMap = req.getParameterMap();
        ServletContext context = req.getSession().getServletContext();

        List<HabrahabrNews> yourList = (List<HabrahabrNews>) context.getAttribute("habrahabrNews");

        Boolean archiveFlag = false;

        if ((Boolean) req.getSession().getAttribute("habrahabrIndexFlag") == false) {
            archiveFlag = true;
        }
        req.getSession().setAttribute("habrahabrArchiveFlag", archiveFlag);

        for (HabrahabrNews list : yourList) {
            HabrahabrNewsArchive habrahabrNewsArchive = new HabrahabrNewsArchive(list.getTitle(), list.getDescription(), list.getLink());
            HabrahabrArchiveJpaDao habrahabrArchiveJpaDao = new HabrahabrArchiveJpaDao();
            habrahabrArchiveJpaDao.create(habrahabrNewsArchive);
        }

        res.sendRedirect("HabrahabrMainNewsController");

    }
}
