
package com.neu.pojo;


public class Product {
    
    private long id;
    private String companyName;
    private String productName;
    private int price;
    private String username;
    
        
    public Product(){
        
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
//
//    public String getCourseCRN() {
//        return courseCRN;
//    }
//
//    public void setCourseCRN(String courseCRN) {
//        this.courseCRN = courseCRN;
//    }

    
    
}
