package com.bilgeadam;

import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainPost {

    public static void main(String[] args) {

        Post post= Post.builder()
                .content("post1 içerik")
                .date(LocalDateTime.now().minusWeeks(1))
                .userId(1L)
                .build();
        Post post2= Post.builder()
                .content("post2 içerik")
                .date(LocalDateTime.now().minusDays(3))
                .userId(1L)
                .build();
        Post post3= Post.builder()
                .content("post3 içerik")
                .userId(1L)
                .build();
        Post post4= Post.builder()
                .content("post4 içerik")
                .userId(2L)
                .build();
        Session session= HibernateUtility.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        session.save(post);
        session.save(post2);
        session.save(post3);
        session.save(post4);
        transaction.commit();
        session.close();


    }

}
