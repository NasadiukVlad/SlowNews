package com.slownews.controller;

import com.slownews.dao.ArchiveJpaDao;
import com.slownews.domain.NewsArchive;
import com.slownews.model.BBCNews;
import com.slownews.model.HabrahabrNews;
import com.sun.javafx.collections.MappingChange;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ���� on 03.01.2016.
 */
public class HabrahabrArchivePageController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();

        ArchiveJpaDao archiveJpaDao = new ArchiveJpaDao();
        NewsArchive newsArchive = new NewsArchive();
        List<NewsArchive> news = archiveJpaDao.getAll();

        ArrayList archive = new ArrayList();

        for (int i = 0; i < news.size(); i++) {
            HashMap items = new HashMap();
            items.put("title", news.get(i).getTitle());
            items.put("description", news.get(i).getDescription());
            items.put("link", news.get(i).getLink());
            archive.add(items);
                /*newsArchive.setTitle(news.get(i).getTitle());
                newsArchive.setDescription(news.get(i).getDescription());
                newsArchive.setLink(news.get(i).getLink());*/
        }


        context.setAttribute("archiveList", archive);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/HabrahabrArchive.jsp");
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

            NewsArchive newsArchive = new NewsArchive(req.getParameter("title"),req.getParameter("description"), req.getParameter("link"));
            ArchiveJpaDao archiveJpaDao = new ArchiveJpaDao();
            archiveJpaDao.create(newsArchive);

    }
}
