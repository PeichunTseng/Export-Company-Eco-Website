
package com.neu.controller;

import com.neu.dao.MessageDao;
import com.neu.dao.ShippingUserDao;
import com.neu.dao.ManufacturingUserDao;
import com.neu.dao.UserDao;
import com.neu.pojo.Login;
import com.neu.pojo.ManufacturingLogin;
import com.neu.pojo.ShippingLogin;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

public class AuthenticationController extends AbstractController {

    public AuthenticationController() {
        
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        ModelAndView mv = null;

        String option = request.getParameter("option") == null ? "" : request.getParameter("option");

        if (session.getAttribute("USER") == null && option.equals("")) {
            return new ModelAndView("loginPage");
        }
        
        if (option.equals("registerSuccess")) {
            return new ModelAndView("wholeCompany");
        }
        
        
        String name = getApplicationContext().getApplicationName();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserDao user = (UserDao) getApplicationContext().getBean("userDAO");//通過name獲取 Bean.
        ShippingUserDao shippinguser = (ShippingUserDao) getApplicationContext().getBean("shippinguserDAO");
        ManufacturingUserDao manufacturinguser = (ManufacturingUserDao) getApplicationContext().getBean("manufacturinguserDAO");
        MessageDao msg = (MessageDao) getApplicationContext().getBean("messageDAO");
        switch (option) {
            
            case "home":
                String choice = request.getParameter("choice");
                if (choice.equals("shipping company")) 
                {
                    mv = new ModelAndView("shippingCompany");
                } 
                else if (choice.equals("manufacturing company")) 
                {
                    mv = new ModelAndView("manufacturingCompany");
                }
                else if (choice.equals("export company")) 
                {
                    mv = new ModelAndView("loginPage");
                }
                break;
                
            case "logout":
                session.invalidate();
                mv = new ModelAndView("wholeCompany");
                break;
            case "login":
                Login loggedUser = user.authenticateLogin(userName, password);
                
                if (loggedUser == null) {
                    mv = new ModelAndView("loginPage");
                } 
                
                else {
                    //Redirect to messages controller
                    //response.sendRedirect("/SpringMVC/messageHome.htm");
                    session.setAttribute("USER", loggedUser);
                    mv = new ModelAndView(new RedirectView("/Webtool_Final/exportHome.htm", false));
                    

                }
                break;
            case "shippinglogin":
                ShippingLogin shippingUser = shippinguser.authenticateLogin(userName, password);
                if(shippingUser == null) {
                    mv = new ModelAndView("shippingCompany");
                } 
                else {
                    session.setAttribute("ShippingUSER", shippingUser);
                    mv = new ModelAndView("shippinginfo");
                    
                }
                break;
                
            case "manufacturinglogin":
                ManufacturingLogin manufacturingUser = manufacturinguser.authenticateLogin(userName, password);
                if (manufacturingUser == null) {
                    mv = new ModelAndView("manufacturingCompany");
                }
                else
                {
                    session.setAttribute("USER", manufacturingUser);
                    mv = new ModelAndView("manufacturinginfo");
                    
                }
                break;
            case "register":
                
                if (userName.length() == 0 || password.length() ==0) {
                    mv = new ModelAndView("loginPage");
                    break;
                }
                if(user.getUsers(userName).size()!=0){
                    mv = new ModelAndView("userExisted");
                    break;
                }
                int regiesterUser = user.addUser(userName, password);
                if (regiesterUser == 1) {
                    Login loggeduser = new Login(userName, password);
                    session.setAttribute("USER", loggeduser);
                     mv = new ModelAndView("registerSuccess");
                    //mv = new ModelAndView(new RedirectView("/Webtool_Final/messageHome.htm", false));
                    //Redirecet to messages controller
                } else {
                    mv = new ModelAndView("loginPage");
                }
                break;
                
            case "shippingregister":

                if (userName.length() == 0 || password.length() == 0) {
                    mv = new ModelAndView("shippingCompany");
                    break;
                }
                if(shippinguser.getUsers(userName).size()!=0){
                    mv = new ModelAndView("userExisted");
                    break;
                }
                int regiestershipping = shippinguser.addUser(userName, password);
                if (regiestershipping == 1) {
                    ShippingLogin loggeduser = new ShippingLogin(userName, password);
                    session.setAttribute("USER", loggeduser);
                    mv = new ModelAndView("registerSuccess");
                    //mv = new ModelAndView(new RedirectView("/Webtool_Final/messageHome.htm", false));
                    //Redirecet to messages controller
                } else {
                    mv = new ModelAndView("shippingCompany");
                }
                break;
            case "manufacturingregister":

                if (userName.length() == 0 || password.length() == 0) {
                    mv = new ModelAndView("manufacturingCompany");
                    break;
                }
                if (manufacturinguser.getUsers(userName).size() != 0) {
                    mv = new ModelAndView("userExisted");
                    break;
                }
                int regiestermanufacturing = manufacturinguser.addUser(userName, password);
                if (regiestermanufacturing == 1) {
                    ManufacturingLogin loggeduser = new ManufacturingLogin(userName, password);
                    session.setAttribute("USER", loggeduser);
                    mv = new ModelAndView("registerSuccess");
                    //mv = new ModelAndView(new RedirectView("/Webtool_Final/messageHome.htm", false));
                    //Redirecet to messages controller
                } else {
                    mv = new ModelAndView("manufacturingCompany");
                }
                break;

            default:
                mv = new ModelAndView(new RedirectView("/Webtool_Final/messageHome.htm", false));
        }
        return mv;
    }
}
