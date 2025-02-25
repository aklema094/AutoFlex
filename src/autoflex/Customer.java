
package autoflex;

public class Customer {
      private String cusId;
    private String name;
    
    Customer(String c,String n){
        this.cusId = c;
        this.name = n;
    }
    
    public String getCustomerId(){
        return cusId;
    }
    public String getCustomerName(){
        return name;
    }
}
