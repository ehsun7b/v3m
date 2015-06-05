package com.ehsunbehravesh.varzesh3mobile.servlet;

import com.ehsunbehravesh.varzesh3mobile.bean.TablesBeanLocal;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ehsun Behravesh <ehsun.behravesh@mimos.my>
 */
@WebServlet(name = "TablesServlet", urlPatterns = {"/tables"})
public class TablesServlet extends HttpServlet {

    @Inject
    private TablesBeanLocal tablesBean;
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/tables.jsp");
        
        request.setAttribute("iranLeagueTable", tablesBean.iranLeagueTable());
        request.setAttribute("spainLeagueTable", tablesBean.spainLeagueTable());
        request.setAttribute("englandLeagueTable", tablesBean.englandLeagueTable());
        request.setAttribute("germanyLeagueTable", tablesBean.germanyLeagueTable());
        request.setAttribute("italyLeagueTable", tablesBean.italyLeagueTable());
        request.setAttribute("franceLeagueTable", tablesBean.franceLeagueTable());
        
        dispatcher.forward(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Tables servlet";
    }

}
