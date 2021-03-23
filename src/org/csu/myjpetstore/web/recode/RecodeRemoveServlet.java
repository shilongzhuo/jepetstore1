package org.csu.myjpetstore.web.recode;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.domain.Recode;
import org.csu.myjpetstore.persistence.impl.RecodeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecodeRemoveServlet extends HttpServlet {
    private String recordItemid;
    private static final String VIEW_RECORD="/WEB-INF/jsp/account/Record.jsp";
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            RecodeDAO recodeDAO = new RecodeDAO();
            recordItemid = request.getParameter("recodeItemid");
            recodeDAO.delete(recordItemid);
            List<Recode> recodeList = new ArrayList<Recode>();
            recodeList = recodeDAO.selectByUsername(Account.accountUsername);
            int x = recodeList.size();
            session.setAttribute("recodes", recodeList);
            request.getRequestDispatcher(VIEW_RECORD).forward(request, response);
    }
}
