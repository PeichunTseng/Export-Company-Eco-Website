
package com.neu.controller;

import com.neu.dao.ProductDao;
import com.neu.dao.ShippingDao;
import com.neu.pojo.ManufacturingLogin;
import com.neu.pojo.ShippingLogin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;


public class AddCompanyController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        HttpSession session = hsr.getSession();
        ModelAndView mv = null;
        ManufacturingLogin loggedUser = (ManufacturingLogin) session.getAttribute("USER");
        ShippingLogin shiploggedUser = (ShippingLogin) session.getAttribute("ShippingUSER");
        if (loggedUser == null && shiploggedUser==null) {
            hsr1.sendRedirect("/");
        } 
        else if(loggedUser != null && shiploggedUser==null)
        {
            String username = loggedUser.getUsername();
            String requestType = (String) hsr.getParameter("formtype");
            System.out.println("requestType " + requestType);
            if (requestType.equals("submitproduct")) 
            {
                String companyname = hsr.getParameter("companyname");
                String productname = hsr.getParameter("productname");
                int price=0;
                ProductDao proDao = (ProductDao) getApplicationContext().getBean("productDAO");
                if (companyname.length()==0||productname.length()==0||hsr.getParameter("price").length()==0) 
                {
                    mv = new ModelAndView("manufacturinginfo");
                } 
                else
                {
                    price=Integer.parseInt(hsr.getParameter("price"));
                    if (proDao.addProduct(companyname, productname,price, username) == 1) 
                    {
                        mv = new ModelAndView("addProductSuccess");
                    } 
                    else 
                    {
                        mv = new ModelAndView("error");
                    }
                }
            } 

            else if (requestType.equals("home")) {
                mv =  new ModelAndView("manufacturinginfo");
            } 
            else if (requestType.equals("logout")) {
                session.invalidate();
                mv =  new ModelAndView("wholeCompany");
            }
            //mv =  new ModelAndView("error");
        }
        else if(loggedUser == null && shiploggedUser!=null)
        {
            String username = shiploggedUser.getUsername();
            String requestType = (String) hsr.getParameter("formtype");
            System.out.println("requestType " + requestType);
            if (requestType.equals("submitshipping")) 
            {
                String companyname = hsr.getParameter("companyname");
                String destination = hsr.getParameter("destination");
                int price=0;
                ShippingDao shiDao = (ShippingDao) getApplicationContext().getBean("shippingDAO");
                if (companyname.length() == 0 || destination.length() == 0 || hsr.getParameter("price").length() == 0) {
                    mv = new ModelAndView("shippinginfo");
                }
                else
                {
                    price = Integer.parseInt(hsr.getParameter("price"));
                    if (shiDao.addShipping(companyname, destination, price, username) == 1) 
                    {
                        mv = new ModelAndView("addShippingSuccess");
                    } 
                    else 
                    {
                        mv = new ModelAndView("error");
                    }
                }
            } 
            else if (requestType.equals("shippinghome")) 
            {
                mv = new ModelAndView("shippinginfo");
            } 
            else if (requestType.equals("logout")) 
            {
                session.invalidate();
                mv = new ModelAndView("wholeCompany");
            }
                    
        }
        return mv;
    }
}
