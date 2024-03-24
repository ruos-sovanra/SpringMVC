package com.example.springmvc.dto;

import lombok.Builder;
import lombok.Data;



@Builder
public record ProductResponse
        (int id,String title, String description, float price, String imageUrl){

}
