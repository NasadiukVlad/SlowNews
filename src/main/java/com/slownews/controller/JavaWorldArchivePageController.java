package com.slownews.controller;

import com.slownews.dao.HabrahabrArchiveJpaDao;
import com.slownews.dao.JavaWorldArchiveJpaDao;
import com.slownews.domain.HabrahabrNewsArchive;
import com.slownews.domain.JavaWorldNewsArchive;
import com.slownews.domain.NewsArchive;
import com.slownews.model.HabrahabrNews;
import com.slownews.model.JavaWorldNews;
import com.slownews.service.JavaWorldArchiveImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Влад on 03.01.2016.
 */
public class JavaWorldArchivePageController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        JavaWorldArchiveImpl javaWorldArchive = new JavaWorldArchiveImpl();
        ArrayList archive = new ArrayList();
        archive = javaWorldArchive.getJavaWorldNewsArchive();

        context.setAttribute("javaWorldArchiveNewsList", archive);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/JavaWorldArchive.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Map<String, String[]> parametersMap = req.getParameterMap();
        ServletContext context = req.getSession().getServletContext();

        List<JavaWorldNews> yourList = (List<JavaWorldNews>) context.getAttribute("javaNews");

        Boolean archiveFlag = false;

        if ((Boolean) req.getSession().getAttribute("javaWorldIndexFlag") == false) {
            archiveFlag = true;
        }
        req.getSession().setAttribute("javaWorldAchiveFlag", archiveFlag);

        for (JavaWorldNews list : yourList) {
            JavaWorldNewsArchive javaWorldNewsArchive = new JavaWorldNewsArchive(list.getTitle(), list.getDescription(), list.getLink());
            JavaWorldArchiveJpaDao javaWorldArchiveJpaDao = new JavaWorldArchiveJpaDao();
            javaWorldArchiveJpaDao.create(javaWorldNewsArchive);
        }

        res.sendRedirect("JavaWorldNewsController");

    }


}
