package com.bilgeadam.repository.criteriaquery;

import com.bilgeadam.repository.ICrud;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/*
1- Icrud da findall findById ve fındByUsername metotlarını tanımlayıp  repositorylere entegre edelim
2- findAll metonudu critereia query ile yazıp controllerda çalıstıralım
3-findby id metodunu yazalım




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
    public Optional<User> findById(Long id) {
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
//        criteriaQuery.select(root);
//        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"),id));
        User user=null;
        try {
            user=entityManager.createQuery(criteriaQuery).getSingleResult();
            System.out.println(user);
            return Optional.of(user);
        }catch (Exception e){
            System.out.println("Kullanıcı bulunamadı!!!!");
            return Optional.empty();
        }
    }
}
