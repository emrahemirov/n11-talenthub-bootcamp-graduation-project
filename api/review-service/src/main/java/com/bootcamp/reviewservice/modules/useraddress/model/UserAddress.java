package com.bootcamp.reviewservice.modules.useraddress.model;

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
@Table(name = "user_addresses")
public class UserAddress extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "address_line")
    private String addressLine;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "floor")
    private String floor;

    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Column(name = "is_preferred")
    private Boolean isPreferred;

    @Column(name = "address_type")
    @Enumerated(value = EnumType.STRING)
    private UserAddressType addressType;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", floor='" + floor + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", addressType=" + addressType +
                ", user=" + user +
                '}';
    }
}
