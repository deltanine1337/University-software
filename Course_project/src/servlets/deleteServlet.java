package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import Tables.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class deleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("table")) {
            case "developer":
                int devid = Integer.parseInt(req.getParameter("id"));
                try {
                    DeveloperDAO.deleteDeveloper(devid);
                    getServletContext().getRequestDispatcher("/WEB-INF/deleteForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "discipline":
                int disid = Integer.parseInt(req.getParameter("id"));
                try {
                    DisciplineDAO.deleteDiscipline(disid);
                    getServletContext().getRequestDispatcher("/WEB-INF/deleteForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "software":
                int softid = Integer.parseInt(req.getParameter("id"));
                try {
                    SoftwareDAO.deleteSoftware(softid);
                    getServletContext().getRequestDispatcher("/WEB-INF/deleteForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareDiscipline":
                int softwareid = Integer.parseInt(req.getParameter("id1"));
                int discid = Integer.parseInt(req.getParameter("id2"));
                try {
                    SoftwareDisciplineDAO.deleteSoftwareDiscipline(softwareid,discid);
                    getServletContext().getRequestDispatcher("/WEB-INF/deleteForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareType":
                int softwaretypeid = Integer.parseInt(req.getParameter("id"));
                try {
                    SoftwareTypeDAO.deleteSoftwareType(softwaretypeid);
                    getServletContext().getRequestDispatcher("/WEB-INF/deleteForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "spec":
                int specid = Integer.parseInt(req.getParameter("id"));
                try {
                    SpecDAO.deleteSpec(specid);
                    getServletContext().getRequestDispatcher("/WEB-INF/deleteForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "specSoftware":
                int specId = Integer.parseInt(req.getParameter("id1"));
                int softwareId = Integer.parseInt(req.getParameter("id2"));
                try {
                    Specs_SoftwareDAO.deleteSpecSoftware(specId, softwareId);
                    getServletContext().getRequestDispatcher("/WEB-INF/deleteForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            default:
                req.setAttribute("ex", "Таблица не существует.");
                getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                break;
        }
    }
}
