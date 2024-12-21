package ru.kpfu.servlets.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private int id;
    private int userId;
    private String userName;
    private int rating;
    private String comment;
    private Timestamp createdAt;

}

