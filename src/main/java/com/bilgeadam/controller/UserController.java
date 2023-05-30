package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.*;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class UserController {

    public static void main(String[] args) {
        createUsers();
    }

    public  static  void createUsers(){
        Session session= HibernateUtility.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
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
    session.save(user1);
    session.save(user2);
    session.save(user3);
    session.save(user4);
    session.save(user5);
    transaction.commit();
    session.close();
    }



}
