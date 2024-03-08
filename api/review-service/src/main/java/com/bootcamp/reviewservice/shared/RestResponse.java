package com.bootcamp.reviewservice.shared;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RestResponse<T> {

    private T data;
    private Object error;
    private LocalDateTime responseDate;
    private boolean isSuccess;

    public RestResponse(T data, Object error, boolean isSuccess) {
        this.data = data;
        this.error = error;
        this.isSuccess = isSuccess;
        this.responseDate = LocalDateTime.now();
    }

    public static <T> RestResponse<T> of(T t) {
        return new RestResponse<>(t, null, true);
    }

    public static <T> RestResponse<T> error(Object t) {
        return new RestResponse<>(null, t, false);
    }

    public static <T> RestResponse<T> empty() {
        return new RestResponse<>(null, null, true);
    }
}