package com.app.rewardprogram.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AuditData {

    protected LocalDateTime auditCreationDate;

    protected LocalDateTime auditModificationDate;

    protected String auditModificationProcess;

    protected boolean auditDeleted;
}
