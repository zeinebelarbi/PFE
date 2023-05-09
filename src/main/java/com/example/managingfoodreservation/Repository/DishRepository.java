package com.example.managingfoodreservation.Repository;


import com.example.managingfoodreservation.model.Dish;
import com.example.managingfoodreservation.wrapper.DishWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.List;

public interface DishRepository  extends JpaRepository<Dish,Integer> {


    @Query("select new  com.example.managingfoodreservation.wrapper.DishWrapper(d.iddish ,d.dishname,d.description,d.price,d.status,d.menuCategory.idMenuCategory,d.menuCategory.menucategoryname )from Dish d")
    List<DishWrapper> getAllDish();

    @Modifying
    @Transactional
    @Query("update Dish d set d.status = :status where d.iddish = :iddish")
    void updateStatus(@Param("status") String status, @Param("iddish") Integer iddish);

    @Query(name = "Dish.getDishByMenuCategory")
    List<DishWrapper> getDishByMenuCategory(@Param("idMenuCategory") Integer idMenuCategory);


    DishWrapper getDishById(@Param("iddish") Integer iddish);
}