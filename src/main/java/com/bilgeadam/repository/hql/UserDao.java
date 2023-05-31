package com.bilgeadam.repository.hql;

import com.bilgeadam.repository.ICrud;
import com.bilgeadam.repository.entity.Name;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
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

    public Optional<User> findByUsername(String username){
//        String hql="select u from User u where u.username='"+username+"'";
        String hql="select u from User u where u.username=:myusername";
        EntityManager entityManager=HibernateUtility.getSessionFactory().createEntityManager();;
        TypedQuery<User> typedQuery=entityManager.createQuery(hql, User.class);
        typedQuery.setParameter("myusername",username);

        User user=null;
        try {
            user=typedQuery.getSingleResult();
            System.out.println(user);
        }catch (Exception e){
            System.out.println("Kullanıcı bulunamadı !!!");
        }
        return  Optional.ofNullable(user);

    }

    public List<Name> findAllName(){
        String hql="select  name from User";
        Session session=HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Name> typedQuery   =session.createQuery(hql, Name.class);
        List<Name> nameList = typedQuery.getResultList();
        session.close();
        return  nameList;
    }

    public List<String> findAllFirstName(){
        String hql="select u.name.firstName from User as u";
        Session session=HibernateUtility.getSessionFactory().openSession();
        TypedQuery<String> typedQuery=session.createQuery(hql,String.class);
        return    typedQuery.getResultList();
    }

    public List<User> findAllFirstNameStartWith(String value){
        String hql="select u from User as u  where u.name.firstName like:x";
        Session session=HibernateUtility.getSessionFactory().openSession();
        TypedQuery<User> typedQuery=session.createQuery(hql,User.class);
        typedQuery.setParameter("x",value+"%");
        return    typedQuery.getResultList();
    }

    public Long  sumPostCount(){
        String hql="select sum(postCount) from User ";
        Session session=HibernateUtility.getSessionFactory().openSession();
        TypedQuery<Long> typedQuery=session.createQuery(hql,Long.class);
        return  typedQuery.getSingleResult();
    }


}
