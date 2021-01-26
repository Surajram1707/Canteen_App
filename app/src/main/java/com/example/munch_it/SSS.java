package com.example.munch_it;

public class SSS {
    String orderlis;
    String amt;
    String name;


    public SSS()
    {

    }
    public SSS( String name,String orderlis,String amt) {

        this.orderlis =orderlis ;
        this.amt = amt;
        this.name=name;

      //  this.id=id;

    }

   public String getName() {
        return name;
    }





    public String getOrderlis() {
        return orderlis;
    }

    public String getAmt() {
        return amt;
    }


}
