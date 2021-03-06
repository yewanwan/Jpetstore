package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConfirmEditServlet extends HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String EDITACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";

    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");
        String languages = request.getParameter("languagePreference");
        String categories = request.getParameter("favouriteCategoryId");

        boolean listOption;
        if(request.getParameter("listOption") != null){
            listOption =  true;
        }else{
            listOption = false;
        }

        boolean bannerOption;
        if(request.getParameter("bannerOption") != null){
            bannerOption =  true;
        }else{
            bannerOption = false;
        }

        if(password.equals(repeatedPassword)){
            HttpSession session = request.getSession();
            account = (Account)session.getAttribute("account");
            account.setPassword(password);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            account.setPhone(phone);
            account.setAddress1(address1);
            account.setAddress2(address2);
            account.setCity(city);
            account.setState(state);
            account.setZip(zip);
            account.setCountry(country);
            account.setLanguagePreference(languages);
            account.setFavouriteCategoryId(categories);
            account.setListOption(listOption);
            account.setBannerOption(bannerOption);

            AccountService accountService = new AccountService();
            accountService.updateAccount(account);
            // 覆盖原来的account
            session.setAttribute("account",account);

            request.getRequestDispatcher(MAIN).forward(request,response);
           }else{
            String message = "password error";
            request.setAttribute("message",message);
            request.getRequestDispatcher(EDITACCOUNT).forward(request,response);
        }
    }

}
