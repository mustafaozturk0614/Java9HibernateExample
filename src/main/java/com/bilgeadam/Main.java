package com.bilgeadam;

import com.bilgeadam.repository.entity.*;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Session session= HibernateUtility.getSessionFactory().openSession();
       Transaction transaction= session.beginTransaction();

        Map<EAddressType, Address> map=new HashMap<>();
        map.put(EAddressType.OTHER,new Address("Ankara","Turkiye"));
        map.put(EAddressType.WORK,new Address("İstanbul","Turkiye"));
        List<String> interests=new ArrayList<>(List.of("Pc oyunları","Muzik","Resim"));
       User user1= User.builder()
               .name(
                       Name.builder()
                               .firstName("Doruk")
                               .lastName("Tokinan")
                               .build()
               )
               .interests(interests)
               .username("doruk")
               .password("123")
               .gender(EGender.MAN)
               .addresses(map)
               .build();
       session.save(user1);
       transaction.commit();
       session.close();

    }
}