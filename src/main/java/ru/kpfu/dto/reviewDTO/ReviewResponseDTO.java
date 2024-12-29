package ru.kpfu.dto.reviewDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private int id;
    private int userId;
    private String userName;
    private int rating;
    private String comment;
    private Timestamp createdAt;
}