package com.bilgeadam;

import com.bilgeadam.repository.entity.EGender;
import com.bilgeadam.repository.entity.Name;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Session session= HibernateUtility.getSessionFactory().openSession();
       Transaction transaction= session.beginTransaction();
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
               .build();
       session.save(user1);
       transaction.commit();
       session.close();

    }
}