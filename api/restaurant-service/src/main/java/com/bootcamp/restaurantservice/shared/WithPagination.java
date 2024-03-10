package com.bootcamp.restaurantservice.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithPagination<T> {

    private List<T> content;
    private Boolean hasPrevious;
    private Boolean hasNext;
    private Long totalItems;
    private Integer totalPages;
    private Integer pageSize;


    public static <T> WithPagination<T> of(
            Page<?> pageAble,
            List<T> content
    ) {
        return new WithPagination<>(
                content,
                pageAble.hasPrevious(),
                pageAble.hasNext(),
                pageAble.getTotalElements(),
                pageAble.getTotalPages(),
                pageAble.getSize());
    }


}