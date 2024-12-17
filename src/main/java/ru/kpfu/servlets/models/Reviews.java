package ru.kpfu.servlets.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Reviews {
    private int id;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
