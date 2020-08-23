package com.example.mongodb.service.impl;

import com.example.mongodb.entity.Person;
import com.example.mongodb.repository.PersonRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Person savePerson(Person person) {
        mongoTemplate.save(person);
        return person;
    }

    @Override
    public Person findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return (Person) mongoTemplate.findOne(query,Person.class);
    }

    @Override
    public List<Person> findAll() {
        return mongoTemplate.findAll(Person.class);
    }

    @Override
    public long updatePerson(Person person) {
        Query query = new Query(Criteria.where("id").is(person.getId()));
        Update update = new Update().set("name",person.getName());
        //更新查到结果的第一条
        UpdateResult result = mongoTemplate.updateFirst(query,update,Person.class);
        //跟新查到的所有条数
        //mongoTemplate.updateMulti(query,update,Person.class);
        if (null != result){
            return result.getMatchedCount();
        }else {
            return 0;
        }
    }

    @Override
    public long deletePerson(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        DeleteResult result = mongoTemplate.remove(query,Person.class);
        if (null != result){
            return result.getDeletedCount();
        }else {
            return 0;
        }
    }
}
