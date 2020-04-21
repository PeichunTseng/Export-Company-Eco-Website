
package com.neu.controller;


import com.neu.dao.ProductDao;
import com.neu.dao.ShippingDao;
import com.neu.pojo.Product;
import com.neu.pojo.Login;
import com.neu.pojo.Shipping;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

public class SearchController extends AbstractController {
    
    public SearchController() {
    }
    
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        Login loggedUser = (Login) session.getAttribute("USER");
        if(loggedUser == null){
            response.sendRedirect("/");
        } else {
        String username=loggedUser.getUsername();
//        String searchString = request.getParameter("searchBar");
//        String choice = request.getParameter("choice");
        if(request.getParameter("choice")==null ||request.getParameter("searchBar")==null)
        {
            mv= new ModelAndView("Search_error");
        }
        else
        {
            String searchString = request.getParameter("searchBar");
            String choice = request.getParameter("choice");
            if(choice.equals("productName"))
            {
                ProductDao proDao = (ProductDao) getApplicationContext().getBean("productDAO");
                List<Product> products = proDao.getCourses(searchString);
                mv= new ModelAndView("viewproduct", "products", products);   
            }
            else if(choice.equals("destination"))
            {
                ShippingDao shiDao = (ShippingDao) getApplicationContext().getBean("shippingDAO");
                List<Shipping> shippinges = shiDao.getCourses(searchString);
                mv= new ModelAndView("viewshipping", "shippinges", shippinges);   
            }
        }

        
        }
        return mv;
    }
    
}
