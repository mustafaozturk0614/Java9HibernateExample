package com.bilgeadam.repository.criteriaquery;

import com.bilgeadam.repository.ICrud;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/*
1- Icrud da findall findById ve fındByUsername metotlarını tanımlayıp  repositorylere entegre edelim
2- findAll metonudu critereia query ile yazıp controllerda çalıstıralım
3-




 */
public class UserRepository implements ICrud<User> {

    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    public UserRepository() {
        entityManager= HibernateUtility.getSessionFactory().createEntityManager();
        criteriaBuilder=entityManager.getCriteriaBuilder();
    }

    @Override
    public List<User> findAll() {
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);

        Root<User> root=criteriaQuery.from(User.class);

        criteriaQuery.select(root);

     List<User> userList= entityManager.createQuery(criteriaQuery).getResultList();

        return  userList;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
