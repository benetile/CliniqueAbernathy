package com.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSeqUser {
    @Id
    private String id;
    private int seq;
}
