package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Tables.*;
import dao.*;

public class selectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getParameter("table")){
            case "developer":
                ArrayList<Developer> devs = new ArrayList<Developer>();
                try {
                    devs =  DeveloperDAO.getDevelopers();
                    req.setAttribute("developers", devs);
                    getServletContext().getRequestDispatcher("/WEB-INF/showTable.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", e);
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareType":
                ArrayList<SoftwareType> softwareTypes = new ArrayList<SoftwareType>();
                try {
                    softwareTypes =  SoftwareTypeDAO.getSoftwareTypes();
                    req.setAttribute("softwareTypes", softwareTypes);
                    getServletContext().getRequestDispatcher("/WEB-INF/showTable.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", e);
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "software":
                ArrayList<Software> software = new ArrayList<Software>();
                try {
                    software =  SoftwareDAO.getSoftwares();
                    req.setAttribute("software", software);
                    getServletContext().getRequestDispatcher("/WEB-INF/showTable.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", e);
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "spec":
                ArrayList<Specs> specs = new ArrayList<Specs>();
                try {
                    specs =  SpecDAO.getSpecs();
                    req.setAttribute("specs", specs);
                    getServletContext().getRequestDispatcher("/WEB-INF/showTable.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", e);
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "discipline":
                ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
                try {
                    disciplines =  DisciplineDAO.getDisciplines();
                    req.setAttribute("disciplines", disciplines);
                    getServletContext().getRequestDispatcher("/WEB-INF/showTable.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", e);
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "specSoftware":
                ArrayList<SpecsSoftware> specsSoftwares = new ArrayList<SpecsSoftware>();
                try {
                    specsSoftwares =  Specs_SoftwareDAO.getSpecSoftwares();
                    req.setAttribute("specSoftwares", specsSoftwares);
                    getServletContext().getRequestDispatcher("/WEB-INF/showTable.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", e);
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareDiscipline":
                ArrayList<SoftwareDiscipline> softwareDisciplines = new ArrayList<SoftwareDiscipline>();
                try {
                    softwareDisciplines =  SoftwareDisciplineDAO.getSoftwareDisciplines();
                    req.setAttribute("softwareDiscipline", softwareDisciplines);
                    getServletContext().getRequestDispatcher("/WEB-INF/showTable.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", e);
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
