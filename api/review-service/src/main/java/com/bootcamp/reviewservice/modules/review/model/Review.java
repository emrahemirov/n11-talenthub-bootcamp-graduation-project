package com.bootcamp.reviewservice.modules.review.model;

import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    @Column(name = "rate", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ReviewRate rate;

    @Column(name = "comment")
    private String comment;


    @Transient
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                '}';
    }
}
