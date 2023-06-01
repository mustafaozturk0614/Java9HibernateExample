package com.bilgeadam.repository.criteriaquery;

import com.bilgeadam.repository.ICrud;
import com.bilgeadam.repository.entity.EGender;
import com.bilgeadam.repository.entity.Name;
import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/*
1- Icrud da findall findById ve fındByUsername metotlarını tanımlayıp  repositorylere entegre edelim
2- findAll metonudu critereia query ile yazıp controllerda çalıstıralım
3-findby id metodunu yazalım
4- findbyUsername
5- databsedeki butun isimleri getirelim( Name==>firstName,middleName,lastName)  findAllname(); List<Name>
6- sadece firstname leri donen bir sorgu yazalım
7-ismi dısarıdan girilen bir harfle (M) ile baslayan kullanıcıları getirelim
8-ismi dısarıdan girilen bir harfle (M) ile baslayan ve post count değeri 6 dan buyuk kullanıcıları getirelim
9- Post countların toplamını bulalım
10- en cok post atan kullanıcıyı bulalım
11-Butunkullanıcıların  username gender ve postcount nu donen sorgu  (2 tane yontemi var 2 sinide deneyin)

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

    public  Optional<User> findByUsername(String username){
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("username"),username));
        User user=null;
        try {
            user=entityManager.createQuery(criteriaQuery).getSingleResult();
            System.out.println(user);
        }catch (Exception e){
            System.out.println("Kullanıcı bulunamadı !!!");
        }
        return  Optional.ofNullable(user);
    }

    public List<Name> findAllName(){
        CriteriaQuery<Name> criteriaQuery=criteriaBuilder.createQuery(Name.class);
        Root<User> root=criteriaQuery.from(User.class);
      //  Path<Name> path1=root.get("name");
        criteriaQuery.select(root.get("name"));
    List<Name> nameList = entityManager.createQuery(criteriaQuery).getResultList();

        return  nameList;
    }

    public List<String> findAllFirstName(){
        CriteriaQuery<String> criteriaQuery=criteriaBuilder.createQuery(String.class);
        Root<User> root=criteriaQuery.from(User.class);
//        Path<String> path1=root.get("name").get("firstName");
//        criteriaQuery.select(path1);
      //  2.yontem
        criteriaQuery.select(root.get("name").get("firstName"));
        return    entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<User> findAllFirstNameStartWith(String value) {
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.like(root.get("name").get("firstName"),value+"%" ));

//        Predicate predicate=criteriaBuilder.like(root.get("name").get("firstName"),value+"%" );
//        criteriaQuery.select(root)
//                .where(predicate);

        return  entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<User> findAllFirstNameStartWithAndPostCount(String value) {
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
//        criteriaQuery.select(root)
//                .where(criteriaBuilder.like(root.get("name").get("firstName"),value+"%" ));

       Predicate predicateLike=criteriaBuilder.like(root.get("name").get("firstName"),value+"%" );
       Predicate predicateGt=criteriaBuilder.greaterThan(root.get("postCount"),6);
        criteriaQuery.select(root)
                .where(criteriaBuilder.and(predicateLike,predicateGt));

        return  entityManager.createQuery(criteriaQuery).getResultList();
    }

    public int  sumPostCount(){
        CriteriaQuery<Integer> criteriaQuery=criteriaBuilder.createQuery(Integer.class);
        Root<User> root=criteriaQuery.from(User.class);
 criteriaQuery.select(criteriaBuilder.sum(root.get("postCount")));
  /*      Expression<Integer> expression=criteriaBuilder.sum(root.get("postCount"));
        criteriaQuery.select(expression);*/
        return  entityManager.createQuery(criteriaQuery).getSingleResult();
    }


    public  User findUserMaxPost(){
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);
        Root<User> root=criteriaQuery.from(User.class);
        criteriaQuery.select(root).orderBy(criteriaBuilder.desc(root.get("postCount"))) ;
        System.out.println("Buyukten kucuge sıralama");
        entityManager.createQuery(criteriaQuery).getResultList().forEach(x->  System.out.println(x.getName()+"-"+x.getPostCount()));
      return entityManager.createQuery(criteriaQuery).getResultList().get(0);
    }

    public  User findUserMaxPost2(){
        //1  maximum post sayısını buldugumuz query
        CriteriaQuery<Integer> criteriaQuery=criteriaBuilder.createQuery(Integer.class);
        Root<User> root=criteriaQuery.from(User.class);
        Expression<Integer> postCount =criteriaBuilder.max(root.get("postCount"));
        criteriaQuery.select(postCount);
        Integer max=   entityManager.createQuery(criteriaQuery).getSingleResult();
        //2 max sayıya gore user ı buldugmuz query
        CriteriaQuery<User> criteriaQuery2= criteriaBuilder.createQuery(User.class);
        Root<User> root2=criteriaQuery2.from(User.class);
        Predicate predicate=criteriaBuilder.equal(root2.get("postCount"),max);
        criteriaQuery2.select(root2).where(predicate);
        return  entityManager.createQuery(criteriaQuery2).getSingleResult();
    }

    public  List<Object[]>  getUsernameGenderPostCount(){
    CriteriaQuery< Object[] > criteriaQuery=criteriaBuilder.createQuery(Object[].class);
    Root<User> root=criteriaQuery.from(User.class);
    criteriaQuery.multiselect(root.get("username"),root.get("gender"),root.get("postCount"));
    return  entityManager.createQuery(criteriaQuery).getResultList();
    }
    public  List<Tuple>  getUsernameGenderPostCount2(){
        CriteriaQuery<Tuple> criteriaQuery=criteriaBuilder.createQuery(Tuple.class);
        Root<User> root=criteriaQuery.from(User.class);
        Path<String> pathUsername= root.get("username");
        Path<EGender> pathGender= root.get("gender");
        Path<Integer> pathPostCount= root.get("postCount");
        criteriaQuery.multiselect(pathUsername,pathGender,pathPostCount);
        //2.yontem
      //  criteriaQuery.multiselect(root.get("username"),root.get("gender"),root.get("postCount"));
    List<Tuple> tuple= entityManager.createQuery(criteriaQuery).getResultList();
        System.out.println("=========================");
        tuple.forEach(x-> {
            System.out.println(x.get(pathUsername)+"-"+x.get(pathGender)+"-"+x.get(pathPostCount));
        });
        System.out.println("=========================");
       return  tuple;

    }
}
