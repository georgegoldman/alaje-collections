package com.hydrogenhr.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SQAnswerDTO {
    private String answer;
    private String question;
    private Long user_id;
}
