package com.bootcamp.reviewservice.shared;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Embedded
    BaseAdditionalFields baseAdditionalFields;
}
