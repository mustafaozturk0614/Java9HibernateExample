package com.bilgeadam.repository.hql;

import com.bilgeadam.repository.ICrud;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserDao implements ICrud<User> {


    @Override
    public List<User> findAll() {
        String hql="select  u from User as u";
        EntityManager entityManager= HibernateUtility.getSessionFactory().createEntityManager();
 //       alternatif kullanım
//        Session session=HibernateUtility.getSessionFactory().openSession();
//        TypedQuery<User> typedQuery= session.createQuery(hql, User.class);

        TypedQuery<User> typedQuery= entityManager.createQuery(hql, User.class);
        List<User> userList=typedQuery.getResultList();
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        String hql="select u from User as  u where u.id="+id;
        Session session=HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery=session.createQuery(hql,User.class);
        User user= null;
        try {
            user = typedQuery.getSingleResult();
            System.out.println(user);
            return Optional.of(user);
        } catch (Exception e) {
            System.out.println("Kullanıcı bulunamadı");
            return Optional.empty();
        }finally {
            System.out.println("Kapanıyor");
            session.close();
        }

    }
}
