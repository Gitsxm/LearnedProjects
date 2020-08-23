package com.example.springbootlearning.repository;

import com.example.springbootlearning.domain.Dog;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class DogDao {
    @PersistenceContext
    EntityManager em;

    public Object findADog(){
        String sql = " select * from dog where 1=1 and id = '1'";
        Query query = em.createNativeQuery(sql,Dog.class);
        Dog dog = (Dog)query.getSingleResult();
        return dog;
    }

    public int updateDog(){
        String sql = "update dog set name = '喵喵' where id='1'";
        return  em.createNativeQuery(sql).executeUpdate();
    }

    public int deleteDog(){
        String sql = "delete from dog where id ='2'";
        return em.createNativeQuery(sql).executeUpdate();
    }

    public List findAll(){
        String sql = " select * from dog";
        return em.createNativeQuery(sql).getResultList();
    }
}
