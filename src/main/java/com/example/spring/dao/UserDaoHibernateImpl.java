package com.example.spring.dao;


import com.example.spring.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(show(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
