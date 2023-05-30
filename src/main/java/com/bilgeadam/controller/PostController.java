package com.bilgeadam.controller;

import com.bilgeadam.repository.entity.Post;
import com.bilgeadam.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostController {

    public static void main(String[] args) {
       // createPosts();

    }



    public  static  void createPosts(){
        Session session= HibernateUtility.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        Post post1= Post.builder().content("içerik1").userId(1L).build();
        Post post2= Post.builder().content("içerik2").userId(2L).build();
        Post post3= Post.builder().content("içerik3").userId(3L).build();
        Post post4= Post.builder().content("içerik4").userId(4L).build();
        Post post5= Post.builder().content("içerik5").userId(5L).build();
        Post post6= Post.builder().content("içerik6").userId(4L).build();
        Post post7= Post.builder().content("içerik7").userId(4L).build();
        Post post8= Post.builder().content("içerik8").userId(4L).build();
        Post post9= Post.builder().content("içerik9").userId(5L).build();
        Post post10= Post.builder().content("içerik10").userId(5L).build();
        Post post11= Post.builder().content("içerik11").userId(3L).build();
        Post post12= Post.builder().content("içerik12").userId(2L).build();
        Post post13= Post.builder().content("içerik12").userId(1L).build();
        session.save(post1);
        session.save(post2);
        session.save(post3);
        session.save(post4);
        session.save(post5);
        session.save(post6);
        session.save(post7);
        session.save(post8);
        session.save(post9);
        session.save(post10);
        session.save(post11);
        session.save(post12);
        session.save(post13);
        transaction.commit();
        session.close();
    }
}
