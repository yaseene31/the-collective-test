package com.the.collective.test.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class ServiceProviderDto {
    private Long id;

    private String name;

    private String clientId;

    private String type;

    private String description;

    private String createdByQid;

    private String updatedByQid;

    private String email;

    private Timestamp activationDate;

    private Timestamp expirationDate;

    private Timestamp updatedDate;
}
