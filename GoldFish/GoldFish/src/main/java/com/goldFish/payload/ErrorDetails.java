package com.goldFish.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;


@Data
@Getter
@AllArgsConstructor
public class ErrorDetails {

    private Date timeStamp;

    private String message;

    private String details;

}
