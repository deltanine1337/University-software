package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;

import Tables.*;
import dao.*;

public class addServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("table")) {
            case "software":
                ArrayList<SoftwareType> softwareTypes1 = new ArrayList<SoftwareType>();
                ArrayList<Developer> developers = new ArrayList<Developer>();
                try {
                    softwareTypes1 = SoftwareTypeDAO.getSoftwareTypes();
                    developers = DeveloperDAO.getDevelopers();
                    req.setAttribute("softwareTypes", softwareTypes1);
                    req.setAttribute("developers", developers);
                    getServletContext().getRequestDispatcher("/WEB-INF/insertForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Ошибка подготовки данных");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareDiscipline":
                ArrayList<Software> softwares = new ArrayList<Software>();
                ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
                try {
                    softwares = SoftwareDAO.getSoftwares();
                    disciplines = DisciplineDAO.getDisciplines();
                    req.setAttribute("softwares", softwares);
                    req.setAttribute("disciplines", disciplines);
                    getServletContext().getRequestDispatcher("/WEB-INF/insertForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Ошибка подготовки данных");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "specSoftware":
                ArrayList<Specs> specs = new ArrayList<Specs>();
                ArrayList<Software> softwares1 = new ArrayList<Software>();
                try {
                    specs = SpecDAO.getSpecs();
                    softwares1 = SoftwareDAO.getSoftwares();
                    req.setAttribute("softwares", softwares1);
                    req.setAttribute("specs", specs);
                    getServletContext().getRequestDispatcher("/WEB-INF/insertForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Ошибка подготовки данных");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "developer":
                try{
                    getServletContext().getRequestDispatcher("/WEB-INF/insertForm.jsp").forward(req, resp);
                } catch (Exception e) {
                    req.setAttribute("ex", "Ошибка подготовки данных");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "discipline":
                try{
                    getServletContext().getRequestDispatcher("/WEB-INF/insertForm.jsp").forward(req, resp);
                } catch (Exception e) {
                    req.setAttribute("ex", "Ошибка подготовки данных");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareType":
                try{
                    getServletContext().getRequestDispatcher("/WEB-INF/insertForm.jsp").forward(req, resp);
                } catch (Exception e) {
                    req.setAttribute("ex", "Ошибка подготовки данных");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "spec":
                try{
                    getServletContext().getRequestDispatcher("/WEB-INF/insertForm.jsp").forward(req, resp);
                } catch (Exception e) {
                    req.setAttribute("ex", "Ошибка подготовки данных");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            default:
                req.setAttribute("ex", "Таблицы не существует");
                getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        switch (req.getParameter("table")){
            case "developer":
                try {
                    String name = req.getParameter("devName");
                    if (name.equals(""))
                    {
                        req.setAttribute("ex", "Введите название разработчика.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    DeveloperDAO.addDeveloper(name);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=developer");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно добавить запись в таблицу. Такое название разработчика уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "software":
                try {
                    String name = req.getParameter("softwareName");
                    if (name.equals(""))
                    {
                        req.setAttribute("ex", "Введите название ПО.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    String version = req.getParameter("version");
                    if (version.equals(""))
                    {
                        req.setAttribute("ex", "Введите версию ПО.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    java.sql.Date admDate = null;
                    short numOfLic = -1;
                    try{numOfLic = Short.parseShort(req.getParameter("numOfLic"));}
                    catch (NumberFormatException e)
                    {
                        req.setAttribute("ex", "Количество лицензий введено неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    try {
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                        Date parsed = f.parse(req.getParameter("admDate"));
                        admDate = new java.sql.Date(parsed.getTime());
                    } catch (ParseException e)
                    {
                        req.setAttribute("ex", "Дата введена неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }

                    int typeId = SoftwareTypeDAO.getSoftwareTypeByName(req.getParameter("typeSelector")).getTypeId();
                    int devId = DeveloperDAO.getDeveloperByName(req.getParameter("devSelector")).getDeveloperId();
                    Software software = new Software(0,name,version,numOfLic,admDate,typeId,devId);
                    SoftwareDAO.addSoftware(software);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=software");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно добавить запись в таблицу. Такое название ПО уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "discipline":
                try {
                    String name = req.getParameter("discName");
                    if (name.equals(""))
                    {
                        req.setAttribute("ex", "Введите название дисциплины.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    DisciplineDAO.addDiscipline(name);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=discipline");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно добавить запись в таблицу. Такое название дисциплины уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "spec":
                try {
                    String name = req.getParameter("specName");
                    if (name.equals(""))
                    {
                        req.setAttribute("ex", "Введите название характеристики.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    SpecDAO.addSpec(name);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=spec");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно добавить запись в таблицу. Такое название характеристики уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareType":
                try {
                    String name = req.getParameter("softwareTypeName");
                    if (name.equals(""))
                    {
                        req.setAttribute("ex", "Введите название вида ПО.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    SoftwareTypeDAO.addSoftwareType(name);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=softwareType");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно добавить запись в таблицу. Такое название вида ПО уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareDiscipline":
                try {
                    int softwareId = SoftwareDAO.getSoftwareByName(req.getParameter("softwareSelector")).getSoftwareId();
                    int disciplineId = DisciplineDAO.getDisciplineByName(req.getParameter("disciplineSelector")).getDisciplineId();
                    SoftwareDisciplineDAO.addSoftwareDiscipline(softwareId, disciplineId);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=softwareDiscipline");
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Ошибка добавления. Такая запись уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "specSoftware":
                try {
                    int softwareId = SoftwareDAO.getSoftwareByName(req.getParameter("softwareSelector1")).getSoftwareId();
                    int specId = SpecDAO.getSpecByname(req.getParameter("specSelector")).getSpecId();
                    double value = -1;
                    try {
                        value = Double.parseDouble(req.getParameter("sValue"));
                    }
                    catch (NumberFormatException e) {
                        req.setAttribute("ex", "Значение характеристики введено неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    Specs_SoftwareDAO.addSpecSoftware(specId, softwareId, value);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=specSoftware");
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Ошибка добавления. Такая запись уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
        }

    }
}
