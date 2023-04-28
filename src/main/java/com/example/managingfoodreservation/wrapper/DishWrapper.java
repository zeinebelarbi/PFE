package com.example.managingfoodreservation.wrapper;


import lombok.Data;

@Data
public class DishWrapper {
    Integer  iddish;
    String  dishname ;
    String description;
    Integer price;
    String status;
    Integer idMenuCategory;
   String  menucategoryname;
   public DishWrapper(Integer iddish,String  dishname,String description,Integer price,String status, Integer idMenuCategory,String menucategoryname ){
this.iddish=iddish;
this.dishname=dishname;
this.description=description;
this.price=price;
this.status=status;
this.idMenuCategory=idMenuCategory;
this.menucategoryname=menucategoryname;
   }
    public DishWrapper(Integer iddish ,String dishname){
       this.iddish=iddish;
       this.dishname=dishname;
    }
    public DishWrapper(Integer iddish ,String dishname,String description,Integer price){
        this.iddish=iddish;
        this.dishname=dishname;
        this.description=description;
        this.price=price;
    }

    public DishWrapper() {

    }
}
