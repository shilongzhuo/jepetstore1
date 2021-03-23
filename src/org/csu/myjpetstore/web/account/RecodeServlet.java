package org.csu.myjpetstore.web.account;

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

public class RecodeServlet extends HttpServlet
{
    private static final String RECODE = "/WEB-INF/jsp/account/Record.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Recode> recodeList = new ArrayList<Recode>();
        RecodeDAO recodeDAO = new RecodeDAO();
        recodeList = recodeDAO.selectByUsername(Account.accountUsername);
        int x = recodeList.size();
        session.setAttribute("recodes", recodeList);
        request.getRequestDispatcher(RECODE).forward(request, response);
    }
}
