package org.csu.myjpetstore.web.account;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActiveServlet extends HttpServlet {

    private static final String SIGNONFORM = "/WEB-INF/jsp/account/SignonForm.jsp";
    private Account account = null;
    private AccountService accountService = null;
    public ActiveServlet(){
        super();
        account = new Account();
        accountService = new AccountService();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收激活码
        String code = request.getParameter("code");
        String email = request.getParameter("email");
        // 根据email查询用户
        try {
            account = accountService.getAccountByEmail(email);
            System.out.println("*************************");
            accountService.validateActiveCode(account,code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 页面跳转
        request.getRequestDispatcher(SIGNONFORM).forward(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

}
