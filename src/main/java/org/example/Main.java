package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.model.Ingredient;
import org.example.model.Meal;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Ingredient i1 = new Ingredient("Sol");
        Ingredient i2 = new Ingredient("Šečer");
        Ingredient i3 = new Ingredient("Meso");
        em.persist(i1);
        em.persist(i2);
        em.persist(i3);


        Meal m1 = new Meal("Sarma");
        m1.getIngredients().add(i1);
        m1.getIngredients().add(i2);
        m1.getIngredients().add(i3);
        em.persist(m1);

        List<Meal> meals = em.createQuery("select m from Meal m",Meal.class).getResultList();
        for (Meal m : meals){
            System.out.println("Naziv jela :"+m.getName());
            for (Ingredient i : m.getIngredients()){
                System.out.println("Sastojci : "+i.getName());
            }
        }
        tx.commit();
        em.close();
        emf.close();

    }
}