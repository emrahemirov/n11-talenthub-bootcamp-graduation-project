package com.bootcamp.reviewservice.modules.useraddress.model;

import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.shared.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_addresses")
public class UserAddress extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "address_line", nullable = false)
    private String addressLine;

    @Column(name = "building_number", length = 5, nullable = false)
    private String buildingNumber;

    @Column(name = "floor", length = 5)
    private String floor;

    @Column(name = "apartment_number", length = 5)
    private String apartmentNumber;

    @Column(name = "address_type")
    @Enumerated(value = EnumType.STRING)
    private UserAddressType addressType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
