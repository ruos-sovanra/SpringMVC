package com.example.springmvc.dto;

import lombok.Builder;

@Builder
public record CategoryRequest (String title, String description){

}
