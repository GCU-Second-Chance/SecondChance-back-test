package com.example.secondchanceback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @PackageName : com.example.secondchanceback.entity
 * @FileName : UserRepository
 * @Author : noglass_gongdae
 * @Date : 2024-05-03
 * @Blog : https://blog.naver.com/noglass_gongdae
 * @GitHub :
 */

@Getter
@Setter
@NoArgsConstructor
@Table(name = "second_chance_user")
@ToString
@Entity
public class UserEntity{
    @Id
    private Long id;
    @Column(nullable = false)
    private String nickname;

    private String takeaway;

    private Long sharing;

    public UserEntity(Long id, String nickname, String takeaway, Long sharing){
        this.id = id;
        this.nickname = nickname;
        this.takeaway = takeaway;
        this.sharing = sharing;
    }
}
