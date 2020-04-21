
package com.neu.controller;

import com.neu.dao.MessageDao;
import com.neu.pojo.Login;
import com.neu.dao.BoughtProductDao;
import com.neu.dao.ChooseShippingDao;
import com.neu.dao.ProductDao;
import com.neu.dao.ShippingDao;
import com.neu.pojo.BoughtProduct;
import com.neu.pojo.ChooseShipping;
import com.neu.pojo.Message;
import com.neu.pojo.Product;
import com.neu.pojo.Shipping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

public class ExportController extends AbstractController {

    public ExportController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = null;

        HttpSession session = request.getSession();
        Login loggedUser = (Login) session.getAttribute("USER");

        if (loggedUser == null) 
        {
            response.sendRedirect("/");
        } 
        else 
        {
            String option = request.getParameter("option") == null ? "" : request.getParameter("option");
            MessageDao msgDao = (MessageDao) getApplicationContext().getBean("messageDAO");
            BoughtProductDao bpDao= (BoughtProductDao) getApplicationContext().getBean("boughtproductDAO");
            ProductDao proDao= (ProductDao) getApplicationContext().getBean("productDAO");
            ShippingDao shipDao= (ShippingDao) getApplicationContext().getBean("shippingDAO");
            ChooseShippingDao csDao= (ChooseShippingDao) getApplicationContext().getBean("chooseshippingDAO");
            int price=0;
            switch (option) 
            {
                case "add":
                    //actual send dao code

                    String companyname = request.getParameter("companyname");
                    //int productprice = Integer.parseInt(request.getParameter("productprice"));
                    String productname = request.getParameter("productname");
                    //ProductDao proDao = (ProductDao) getApplicationContext().getBean("productDAO");
                    List<Product> products = proDao.getCourses(productname,companyname);
                    
                    //mv = new ModelAndView("viewproduct", "products", products);
                    for(Product p:products)
                    {
                        price=p.getPrice();
                    }
                    System.out.println(companyname);
                    //System.out.println(price);
                    System.out.println(productname);
                    System.out.println(loggedUser.getUsername());
                    int results = bpDao.addProduct(companyname, productname, price,loggedUser.getUsername());

                    if (results == 1) {

                        mv = new ModelAndView(new RedirectView("/Webtool_Final/exportHome.htm", false));

                    } else {
                        mv = new ModelAndView("error", "message", "Not able to add product");

                    }

                    break;
                    
                case "addship":
                    //actual send dao code

                    String company = request.getParameter("companyname");
                    //int productprice = Integer.parseInt(request.getParameter("productprice"));
                    String destination = request.getParameter("destination");
                    //ProductDao proDao = (ProductDao) getApplicationContext().getBean("productDAO");
                    List<Shipping> shippings = shipDao.getCourses(destination, company);

                    //mv = new ModelAndView("viewproduct", "products", products);
                    for (Shipping p : shippings) {
                        price = p.getPrice();
                    }
                    System.out.println(company);
                    //System.out.println(price);
                    System.out.println(destination);
                    System.out.println(loggedUser.getUsername());
                    int result = csDao.addShipping(company, destination, price, loggedUser.getUsername());

                    if (result == 1) {

                        mv = new ModelAndView(new RedirectView("/Webtool_Final/exportHome.htm", false));

                    } else {
                        mv = new ModelAndView("error", "message", "Not able to add product");

                    }

                    break;
                case "send":
                    //actual send dao code

                    String message = request.getParameter("message");
                    String from = request.getParameter("sender");
                    String to = request.getParameter("recipient");
                    int result1 = msgDao.addMessages(to, from, message);

                    if (result1 == 1) {

                        mv = new ModelAndView(new RedirectView("/Webtool_Final/exportHome.htm", false));

                    } else {
                        mv = new ModelAndView("error", "message", "Not able to send message");

                    }

                    break;
                    
                case "delete":
                    if(request.getParameter("shipId")==null)
                    {
                    long proId = (long)Long.parseLong(request.getParameter("proId")); 
                    bpDao.deleteProductById(proId);
                    RedirectView view = new RedirectView("/Webtool_Final/messageHome.htm");
                    mv = new ModelAndView(view);
                    }
                    else{
                    long shipId = (long) Long.parseLong(request.getParameter("shipId"));
                    csDao.deleteProductById(shipId);
                    
                    RedirectView view = new RedirectView("/Webtool_Final/messageHome.htm");
                    mv = new ModelAndView(view);
                    }
                    
//                case "delete":
//
//                    long proId = (long) Long.parseLong(request.getParameter("proId"));
//                    bpDao.deleteProductById(proId);
//                    RedirectView view = new RedirectView("/Webtool_Final/messageHome.htm");
//                    mv = new ModelAndView(view);

 
                   
//                case "deleteStoreProduct":
//                    long storeproId = (long) Long.parseLong(request.getParameter("proId"));
//                    proDao.deleteProductById(storeproId);
////                    msgDao.deleteMessageById(msgId);
//                    RedirectView storeview = new RedirectView("/Webtool_Final/messageHome.htm");
//                    mv = new ModelAndView(storeview);
                    
                    
//                    
//                case "deleteship":
//                    long shipId = (long) Long.parseLong(request.getParameter("shipId"));
//                    csDao.deleteProductById(shipId);
//                    RedirectView deleteshipview = new RedirectView("/Webtool_Final/messageHome.htm");
//                    mv = new ModelAndView(deleteshipview);

                default:
                    List<BoughtProduct> wholeproduct = bpDao.getByUsername(loggedUser.getUsername());
                    List<ChooseShipping> wholeshipping = csDao.getByUsername(loggedUser.getUsername());
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("wholeproduct", wholeproduct);
                    model.put("wholeshipping", wholeshipping);
                    mv = new ModelAndView("messageHome", "model", model);
            }
        }

        return mv;
    }

}
