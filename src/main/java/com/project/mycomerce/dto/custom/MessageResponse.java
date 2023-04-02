package com.project.mycomerce.dto.custom;

import lombok.Data;

@Data
public class MessageResponse {
    private  String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
