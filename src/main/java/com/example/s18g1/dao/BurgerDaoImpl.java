package com.example.s18g1.dao;

import com.example.s18g1.entity.BreadType;
import com.example.s18g1.entity.Burger;
import com.example.s18g1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao {

    private final EntityManager entityManager;

    @Autowired
    public BurgerDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if(burger == null){
            throw new BurgerException("Burger is not found with given id: " + id, HttpStatus.NOT_FOUND);
        }
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger e", Burger.class);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByPrice(Integer price) {
        TypedQuery<Burger> findByPriceQuery = entityManager.createQuery("SELECT b FROM Burger b WHERE b.price> :price ORDER BY b.price DESC", Burger.class);
        findByPriceQuery.setParameter("price", price);
        return findByPriceQuery.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        TypedQuery<Burger> findByBreadType = entityManager.createQuery("SELECT b FROM Burger b WHERE b.breadType=:breadType ORDER BY b.name DESC", Burger.class);
        findByBreadType.setParameter("breadType", breadType);
        return findByBreadType.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {
        TypedQuery<Burger> findByContent = entityManager.createQuery("SELECT b FROM Burger b WHERE b.contents LIKE CONCAT('%',:content, '%')", Burger.class);
        findByContent.setParameter("content", content);
        return findByContent.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(long id) {
        Burger foundBurger = entityManager.find(Burger.class, id);
        entityManager.remove(foundBurger);
        return foundBurger;
    }
}
