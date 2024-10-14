package com.hydrogenhr.persistence.entity;

import com.google.common.base.Optional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "SQAnswer")
@Table(name = "sq_answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SQAnswer extends BaseEntity{

    @Column(name = "answer")
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "security_question_fk")
    private SecurityQuestion securityQuestion;

}
