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

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "latitude", length = 10)
    private String latitude;

    @Column(name = "longitude", length = 10)
    private String longitude;

    @Column(name = "address_line", nullable = false)
    private String addressLine;

    @Column(name = "building_number", length = 10, nullable = false)
    private String buildingNumber;

    @Column(name = "floor", length = 10)
    private String floor;

    @Column(name = "apartment_number", length = 10)
    private String apartmentNumber;

    @Column(name = "is_preferred")
    private Boolean isPreferred;

    @Column(name = "address_type")
    @Enumerated(value = EnumType.STRING)
    private UserAddressType addressType;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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
