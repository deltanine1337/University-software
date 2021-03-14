package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Tables.*;
import dao.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class changeServlet extends HttpServlet {
    public static SpecsSoftware specsSoftware;
    public static Software software;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = -1;
        int id1 = -1;
        int id2 = -1;
        switch (req.getParameter("table")) {
            case "developer":

                try {
                    try {
                        id = Integer.parseInt(req.getParameter("id"));
                    } catch (NumberFormatException e){
                        req.setAttribute("ex", "ID задан неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    Developer dev = DeveloperDAO.getDeveloperById(id);
                    req.setAttribute("dev", dev);
                    getServletContext().getRequestDispatcher("/WEB-INF/changeForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "discipline":
                try {
                    try { id = Integer.parseInt(req.getParameter("id"));
                    } catch (NumberFormatException e){
                        req.setAttribute("ex", "ID задан неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    String discipline = DisciplineDAO.getDisciplinerById(id).getName();
                    req.setAttribute("discipline", discipline);
                    getServletContext().getRequestDispatcher("/WEB-INF/changeForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "software":
                try {
                    try{
                    id = Integer.parseInt(req.getParameter("id"));}
                    catch (NumberFormatException e){
                        req.setAttribute("ex", "ID задан неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    ArrayList<SoftwareType> softwareTypes = SoftwareTypeDAO.getSoftwareTypes();
                    ArrayList<Developer> developers = DeveloperDAO.getDevelopers();
                    software = SoftwareDAO.getSoftwareById(id);
                    req.setAttribute("softwareTypes1", softwareTypes);
                    req.setAttribute("devs1", developers);
                    req.setAttribute("software", software);
                    req.setAttribute("softwareType1", SoftwareTypeDAO.getSoftwareTypeById(software.getTypeId()).getName());
                    req.setAttribute("dev", DeveloperDAO.getDeveloperById(software.getDeveloperId()).getName());
                    getServletContext().getRequestDispatcher("/WEB-INF/changeForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareDiscipline":
                req.setAttribute("ex", "Редактирование таблицы невозможно.");
                getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                break;
            case "softwareType":
                try {
                    try {
                        id = Integer.parseInt(req.getParameter("id"));
                    }
                    catch (NumberFormatException e){
                        req.setAttribute("ex", "ID задан неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    String softwareType = SoftwareTypeDAO.getSoftwareTypeById(id).getName();
                    req.setAttribute("softwareType2", softwareType);
                    getServletContext().getRequestDispatcher("/WEB-INF/changeForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "spec":
                try {
                    try {
                    id = Integer.parseInt(req.getParameter("id"));}
                    catch (NumberFormatException e){
                        req.setAttribute("ex", "ID задан неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    String spec = SpecDAO.getSpecById(id).getName();
                    req.setAttribute("spec2", spec);
                    getServletContext().getRequestDispatcher("/WEB-INF/changeForm.jsp").forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "specSoftware":
                try {
                    try{
                    id1 = Integer.parseInt(req.getParameter("id1"));
                    id2 = Integer.parseInt(req.getParameter("id2"));}
                    catch (NumberFormatException e){
                        req.setAttribute("ex", "ID заданы неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    String software = SoftwareDAO.getSoftwareById(id2).getName();
                    String spec = SpecDAO.getSpecById(id1).getName();
                    req.setAttribute("software", software);
                    req.setAttribute("spec3", spec);
                    double value = Specs_SoftwareDAO.getSpecSoftwareByIds(id1,id2).getValue();
                    req.setAttribute("value1", value);
                    specsSoftware = new SpecsSoftware(id1, id2, value);
                    getServletContext().getRequestDispatcher("/WEB-INF/changeForm.jsp").forward(req, resp);
                }
                catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Запись  не найдена.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        int id;
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
                    id = Integer.parseInt(req.getParameter("id"));
                    Developer dev = new Developer(id, name);
                    DeveloperDAO.updateDeveloper(dev);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=developer");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно изменить запись. Такое название разработчика уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "software":
                try {
                    short numOfLic = -1;
                    id = Integer.parseInt(req.getParameter("id"));
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
                    try {
                        numOfLic = Short.parseShort(req.getParameter("numOfLic"));
                    } catch (NumberFormatException e)
                    {
                        req.setAttribute("ex", "Количество лицензий введено неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    java.sql.Date admDate = null;
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
                    int typeId = 0;
                    try {
                        typeId = SoftwareTypeDAO.getSoftwareTypeByName(req.getParameter("typeSelector")).getTypeId();
                    } catch (NullPointerException e) {
                        typeId = software.getTypeId();
                    }
                    int devId = 0;
                    try {
                        devId = DeveloperDAO.getDeveloperByName(req.getParameter("devSelector")).getDeveloperId();
                    } catch (NullPointerException | SQLException e){
                        devId = software.getDeveloperId();
                    }
                    Software software = new Software(id,name,version,numOfLic,admDate,typeId,devId);
                    SoftwareDAO.updateSoftware(software);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=software");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно изменить запись. Такое название ПО уже существует.");
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
                    id = Integer.parseInt(req.getParameter("id"));
                    Discipline discipline = new Discipline(id, name);
                    DisciplineDAO.updateDiscipline(discipline);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=discipline");

                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно изменить запись. Такое название дисциплины уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "spec":
                try {
                    String name = req.getParameter("specName");
                    if (name.equals(""))
                    {
                        req.setAttribute("ex", "Введите название характеристики ПО.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    id = Integer.parseInt(req.getParameter("id"));
                    Specs specs = new Specs(id, name);
                    SpecDAO.updateSpec(specs);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=spec");
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно изменить запись. Такое название характеристики уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "softwareType":
                try {
                    id = Integer.parseInt(req.getParameter("id"));
                    String name = req.getParameter("softwareTypeName");
                    if (name.equals(""))
                    {
                        req.setAttribute("ex", "Введите название вида ПО.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    SoftwareType softwareType = new SoftwareType(id, name);
                    SoftwareTypeDAO.updateSoftwareType(softwareType);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=softwareType");
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Невозможно изменить запись. Такое название вида ПО уже существует.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
            case "specSoftware":
                try {
                    double value = -1;
                    try {
                        value = Double.parseDouble(req.getParameter("sValue"));
                    } catch (NumberFormatException e){
                        req.setAttribute("ex", "Значение характеристики введено неверно.");
                        getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                        break;
                    }
                    specsSoftware.setValue(value);
                    Specs_SoftwareDAO.updateValue(specsSoftware);
                    resp.sendRedirect(req.getContextPath()+"/showTable?table=specSoftware");
                } catch (SQLException | ClassNotFoundException e) {
                    req.setAttribute("ex", "Проверьте введённые данные.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                break;
        }
    }
}
