package com.bilgeadam.repository.entity;
/*
    bir user sınıfı olusturalım
    id name username password olan bir user entitiysi olustruup databse e bir kayıt aralım

    1-AdressType enuma olsun ==> ev iş diğer
    2- adress  sınıfımız olsun ==>country,city
    3-Userda bir adress mapi tutatalım   adresetype a karsılık adress tutalım 




 */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private  Name name;
    @Column(nullable = false,unique = true)
    private  String username;
    @Column(nullable = false,length = 32)
    private  String password;
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @ElementCollection
    private List<String> interests;

    @Transient
    private  int age;

    //@Lob--> buyuk veriler için


}
