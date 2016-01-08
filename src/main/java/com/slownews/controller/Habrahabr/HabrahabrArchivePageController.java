package com.slownews.controller.Habrahabr;

import com.slownews.dao.Habrahabr.HabrahabrArchiveJpaDao;
import com.slownews.domain.Habrahabr.HabrahabrNewsArchive;
import com.slownews.model.Habrahabr.HabrahabrNews;
import com.slownews.service.Habrahabr.HabrahabrNewsArchiveImpl;

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

        HabrahabrNewsArchiveImpl habrahabrNewsArchive = new HabrahabrNewsArchiveImpl();
        ArrayList archive = new ArrayList();
        archive = habrahabrNewsArchive.getHabrahabrNewsArchive();

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

        List<HabrahabrNews> yourList = (List<HabrahabrNews>) context.getAttribute("habraNews");

        Boolean archiveFlag = false;

        if ((Boolean) req.getSession().getAttribute("habrahabrIndexFlag") == false) {
            archiveFlag = true;
        }
        req.getSession().setAttribute("habrahabrArchiveFlag", archiveFlag);

        for (HabrahabrNews list : yourList) {
            HabrahabrNewsArchive habrahabrNewsArchive = new HabrahabrNewsArchive(list.getTitle(), list.getDescription(), list.getLink());
            HabrahabrArchiveJpaDao habrahabrArchiveJpaDao = new HabrahabrArchiveJpaDao();
            if (habrahabrArchiveJpaDao.create(habrahabrNewsArchive).equals("duplicate")) {
                break;
            }
        }

        res.sendRedirect("HabrahabrMainNewsController");

    }
}
