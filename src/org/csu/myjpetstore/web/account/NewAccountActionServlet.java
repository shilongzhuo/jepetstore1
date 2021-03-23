package org.csu.myjpetstore.web.account;

import org.csu.myjpetstore.domain.Account;
import org.csu.myjpetstore.persistence.SHAEncryptUtil;
import org.csu.myjpetstore.service.AccountService;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NewAccountActionServlet extends HttpServlet {

    private static final String REMINDER = "/WEB-INF/jsp/account/Reminder.jsp";
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private String input_verification_code;
    private String verification_code;

    public NewAccountActionServlet(){super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {






        request.setCharacterEncoding("UTF-8");// 处理中文乱码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        String email = request.getParameter("account.email");
        String firstName = request.getParameter("account.firstName");
        String lastName = request.getParameter("account.lastName");
        String phone = request.getParameter("account.phone");
        String address1 = request.getParameter("account.address1");
        String address2 = request.getParameter("account.address2");
        String city = request.getParameter("account.city");
        String state = request.getParameter("account.state");
        String zip = request.getParameter("account.zip");
        String country = request.getParameter("account.country");
        String languagePreference = request.getParameter("account.languagePreference");
        String favouriteCategoryId = request.getParameter("account.favouriteCategoryId");
        String listOption = request.getParameter("account.listOption");
        String bannerOption = request.getParameter("account.bannerOption");
        input_verification_code = request.getParameter("verifycode");
        verification_code = CheckCodeServlet.checkCode;

        System.out.println("username=="+username);
        System.out.println("password=="+password);
        System.out.println("repeatedPassword=="+repeatedPassword);


        if ((!password.equals(repeatedPassword)) || (password.equals(""))){
//            提醒用户重新输入密码
            request.setAttribute("error4", "Invalid input_verification_code.  Register failed.");
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request, response);
        }else  if(!verification_code.equals(input_verification_code)){
            request.setAttribute("error5", "Invalid input_verification_code.  Register failed.");
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request, response);
        } else {
            Account account = new Account();
            AccountService accountService = new AccountService();
            account.setUsername(username);
            account.setPassword(password);
            account.setEmail(email);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setPhone(phone);
            account.setAddress1(address1);
            account.setAddress2(address2);
            account.setCity(city);
            account.setState(state);
            account.setZip(zip);
            account.setCountry(country);
            account.setLanguagePreference(languagePreference);
            account.setFavouriteCategoryId(favouriteCategoryId);
            account.setListOption((listOption == "true"));
            account.setBannerOption((bannerOption == "true"));
            account.setCondition("0");//0:未激活  1：激活
            /*try {
                System.out.println("Everything is possible !!!");
                accountService.insertAccount(account);  // 将用户信息存储到数据库表中
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            String actcode = SHAEncryptUtil.SHA256(email); // 将用户email加密后做成激活码
            account.setActcode(actcode);
//            account.setRegisterTime(new Date());
            System.out.println("actcode=="+actcode);

//          调用业务层处理数据
            try {
                System.out.println("Everything is possible !!!");
                accountService.insertAccount(account);
            } catch (Exception e) {
                e.printStackTrace();
            }
//        System.out.println("account.firstName==  "+firstName);
            request.setAttribute("msg", "You have registered successfully! Please go to email to activate!");
            request.getRequestDispatcher(REMINDER).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
