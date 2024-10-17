package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApprovalStatus {
    PENDING('0'),
    APPROVAL('1'),
    UNAPPROVED('2');

    private Character approvalStatus;
}
