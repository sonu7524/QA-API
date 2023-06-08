package com.driver.QAFetcher.Dtos;

import lombok.*;

@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class AnswerRequest {
    private Integer questionId;
    private String answer;

    // Getters and setters
}
