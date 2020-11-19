package cotizacion;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cotizacion.model.DataManager;

@WebServlet(name = "CotizacionServlet", urlPatterns = {"/inicio/*"})
public class CotizacionServlet extends HttpServlet implements Servlet {
    // =========================================== //
    // == Constructor (por defecto) del servlet == //
    // =========================================== //
    public CotizacionServlet() {
        super();
    }
    // ================================ //
    // == Inicialización del servlet == //
    // ================================ //
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        DataManager dataManager = new DataManager();
        dataManager.setDBUrl(config.getInitParameter("dbURL"));
        dataManager.setDBUser(config.getInitParameter("dbUser"));
        dataManager.setDBPass(config.getInitParameter("dbPass"));

        ServletContext context = config.getServletContext();
        context.setAttribute("base", config.getInitParameter("base"));
        context.setAttribute("dataManager", dataManager);
    
        // --------------------------------------------------- //
        // -- Driver para conexión a la base de datos MySQL -- //
        // --------------------------------------------------- //
        try {
            Class.forName(config.getInitParameter("jdbcDriver"));
        }
        catch (ClassNotFoundException exc) {
            System.out.println(exc.toString());
        }
    }
    // ======================= //
    // == Método GET (REST) == //
    // ======================= //
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    // ======================== //
    // == Método POST (REST) == //
    // ======================== //
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base = "/jsp/";
        String url = base + "index.jsp";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "cotizar":
                    url = base + "ResultadoCotizacion.jsp";
                    break;
                default:
                    url = base + "index.jsp";
                    break;
            }
        }
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
