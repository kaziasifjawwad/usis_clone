package com.brainstation.usisclone.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object object;

    public ApiResponse(String message){
        this.message = message;
    }
}
