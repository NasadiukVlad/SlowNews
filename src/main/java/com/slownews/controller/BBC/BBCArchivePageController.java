package com.slownews.controller.BBC;

import com.slownews.dao.BBC.ArchiveJpaDao;
import com.slownews.domain.BBC.NewsArchive;
import com.slownews.model.BBC.BBCNews;
import com.slownews.service.BBC.BBCNewsArchiveImpl;

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
public class BBCArchivePageController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();

        BBCNewsArchiveImpl bbcNewsArchive = new BBCNewsArchiveImpl();

        ArrayList archive = new ArrayList();
        archive = bbcNewsArchive.getBBCArchive();

        context.setAttribute("archiveList", archive);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/BBCArchive.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Map<String, String[]> parametersMap = req.getParameterMap();
        ServletContext context = req.getSession().getServletContext();

        List<BBCNews> yourList = (List<BBCNews>) context.getAttribute("news");

        Boolean archiveFlag = false;

        if ((Boolean) req.getSession().getAttribute("indexFlag") == false) {
            archiveFlag = true;
        }

        req.getSession().setAttribute("archiveFlag", archiveFlag);

        for (BBCNews list : yourList) {
            NewsArchive newsArchive = new NewsArchive(list.getTitle(), list.getDescription(), list.getLink());
            ArchiveJpaDao archiveJpaDao = new ArchiveJpaDao();
            if (archiveJpaDao.create(newsArchive).equals("duplicate")) {
                break;
            }
        }

        res.sendRedirect("IndexPageController");

    }
}
