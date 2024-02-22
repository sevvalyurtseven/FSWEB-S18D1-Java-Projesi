package com.example.s18g1.dao;

import com.example.s18g1.entity.BreadType;
import com.example.s18g1.entity.Burger;

import java.util.List;

public interface BurgerDao {
    Burger save(Burger burger);
    Burger findById(long id);
    List<Burger> findAll();
    List<Burger> findByPrice(Integer price);
    List<Burger> findByBreadType(BreadType breadType);
    List<Burger> findByContent(String content);
    Burger update(Burger burger);
    Burger remove(long id);

}
