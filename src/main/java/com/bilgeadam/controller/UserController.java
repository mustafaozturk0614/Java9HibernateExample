package com.bilgeadam.controller;

import com.bilgeadam.repository.criteriaquery.UserRepository;
import com.bilgeadam.repository.entity.*;
import com.bilgeadam.repository.hql.UserDao;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Tuple;
import java.util.*;

public class UserController {

    public static void main(String[] args) {
        //  createUsers();
        UserRepository userRepository=new UserRepository();// criteria query için
        UserDao userDao=new UserDao();// hql için




        //userRepository.findAll().forEach(x-> System.out.println(x.getId()+"-"+x.getName()+"-"+x.getUsername()));
        //  userDao.findAll().forEach(x-> System.out.println(x.getId()+"-"+x.getName()+"-"+x.getUsername()));
//        userDao.findById(2L);  ;
//        userRepository.findById(2L);
//        Optional<User> user=userDao.findByUsername("zlh");
//        if (user.isEmpty()){
//            System.out.println("Kullanıcı yine bulunamadı");
//            System.out.println(user);
//        }else{
//            System.out.println(user.get().getUsername());
//        }
   //     userRepository.findAllName().forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName()));
//        userDao.findAllName().forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName()));
       // userRepository.findAllFirstName().forEach(System.out::println);
      //  userDao.findAllFirstName().forEach(System.out::println);
      //  userRepository.findAllFirstNameStartWith("M").forEach(x-> System.out.println(x.getId()+"-"+x.getName()+"-"+x.getUsername()));
      //  userDao.findAllFirstNameStartWith("Z").forEach(x-> System.out.println(x.getId()+"-"+x.getName()+"-"+x.getUsername()));
           /* userRepository
                    .findAllFirstNameStartWithAndPostCount("M")
                    .forEach(x-> System.out.println(x.getId()+"-"+x.getName()+"-"+x.getUsername()+"==>"+x.getPostCount()));*/

//        System.out.println(userRepository.sumPostCount());   ;
//        System.out.println(userDao.sumPostCount());   ;
   //     System.out.println(userRepository.findUserMaxPost2());
//        userRepository.getUsernameGenderPostCount().forEach(x->{
//            Object[] array=x;
//            for (Object o:array){
//                System.out.print(o+"-");
//            }
//            System.out.println();
//        });
/*        List<Tuple> tuple=userRepository.getUsernameGenderPostCount2();
        tuple.forEach(x-> {
            System.out.println(x.get(0)+"-"+x.get(1)+"-"+x.get(2));
            //System.out.println(Arrays.toString(x.toArray()));

        });
        System.out.println("-----------");
        userDao.getUsernameGenderPostCount().forEach(x->{
            Object[] array=x;
            for (Object o:array){
                System.out.print(o+"-");
            }
            System.out.println();
        });*/

        userRepository.postCountByGender().forEach(x->{

                    for (Object o :x){
                        System.out.print(o+" ");
                    }
                    System.out.println();
                }
                );

        userRepository.postCountAvgByGender().forEach(x-> System.out.println(Arrays.toString(x.toArray())));

    }

    public  static  void createUsers(){
        Session session= HibernateUtility.getSessionFactory().openSession();
        Transaction transaction= session.getTransaction();
        List<String> list1= Arrays.asList("Astrololi","Sinema");
        List<String> list2= Arrays.asList("Dans","Müzik");
        List<String> list3= Arrays.asList("Seyehat","Tiyatro");
        List<String> list4= Arrays.asList("Kitap","Pc oyunları");

        Map<EAddressType, Address> map1=new HashMap<>();
        map1.put(EAddressType.HOME,new Address("Ankara","Türkiye"));
        map1.put(EAddressType.WORK,new Address("Ankara","Türkiye"));
        Map<EAddressType, Address> map2=new HashMap<>();
        map2.put(EAddressType.HOME,new Address("Ankara","Türkiye"));
        map2.put(EAddressType.WORK,new Address("İstanbul","Türkiye"));
        Map<EAddressType, Address> map3=new HashMap<>();
        map3.put(EAddressType.HOME,new Address("İstanbul","Türkiye"));
        map3.put(EAddressType.WORK,new Address("İstanbul","Türkiye"));

        User user1= User.builder()
                .name(Name.builder().firstName("Zeliha").lastName("Ünlü").build())
                .username("zlh")
                .gender(EGender.WOMAN)
                .interests(list1)
                .addresses(map1)
                .postCount(20)
                .password("1234")
                .build();
        User user2= User.builder()
                .name(Name.builder().firstName("Mustafa").lastName("Öztürk").build())
                .username("musty")
                .gender(EGender.MAN)
                .interests(list2)
                .addresses(map2)
                .postCount(10)
                .password("12345678")
                .build();
        User user3= User.builder()
                .name(Name.builder().firstName("Merve").lastName("Keskin").build())
                .username("merve")
                .gender(EGender.WOMAN)
                .interests(list2)
                .addresses(map2)
                .postCount(5)
                .password("123")
                .build();

        User user4= User.builder()
                .name(Name.builder().firstName("Gokhan").lastName("Kaya").build())
                .username("gokhan")
                .gender(EGender.MAN)
                .interests(list3)
                .addresses(map3)
                .postCount(4)
                .password("1234")
                .build();
        User user5= User.builder()
                .name(Name.builder().firstName("Mert").lastName("Gürel").build())
                .username("mert")
                .gender(EGender.MAN)
                .interests(list4)
                .addresses(map3)
                .postCount(9)
                .password("12345")
                .build();
    }
}
