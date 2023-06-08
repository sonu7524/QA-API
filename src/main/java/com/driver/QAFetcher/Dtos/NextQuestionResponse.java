package com.driver.QAFetcher.Dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NextQuestionResponse {
    private String correctAnswer;
    private QuestionDto nextQuestion;

    // Constructors, getters, and setters
}
