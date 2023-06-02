package com.example.managingfoodreservation.wrapper;


import lombok.Data;

@Data
public class DishWrapper {
    Integer  iddish;
    String  name ;
    String description;
    Float price;
    String status;
    Integer idMenuCategory;
   String  menucategoryname;
   public DishWrapper(Integer iddish,String  name,String description,Float price,String status, Integer idMenuCategory,String menucategoryname ){
this.iddish=iddish;
this.name=name;
this.description=description;
this.price=price;
this.status=status;
this.idMenuCategory=idMenuCategory;
this.menucategoryname=menucategoryname;
   }
    public DishWrapper(Integer iddish ,String name){
       this.iddish=iddish;
       this.name=name;
    }
    public DishWrapper(Integer iddish ,String name,String description,float price){
        this.iddish=iddish;
        this.name=name;
        this.description=description;
        this.price=price;
    }

    public DishWrapper() {

    }
}
