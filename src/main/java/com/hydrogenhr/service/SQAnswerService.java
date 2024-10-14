package com.hydrogenhr.service;

import com.hydrogenhr.model.dto.SQAnswerDTO;
import com.hydrogenhr.persistence.entity.SQAnswer;

public interface SQAnswerService {
    SQAnswer createAnswer(SQAnswerDTO sqAnswer);

    Boolean verifyQuestion( SQAnswerDTO sqAnswerDTO);

}
