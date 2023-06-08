package com.driver.QAFetcher.Controller;

import com.driver.QAFetcher.Dtos.AnswerRequest;
import com.driver.QAFetcher.Dtos.NextQuestionResponse;
import com.driver.QAFetcher.Dtos.QuestionDto;
import com.driver.QAFetcher.Entities.QuestionEntity;
import com.driver.QAFetcher.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QAController {
    private final QuestionService questionService;

    @Autowired
    public QAController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/fetch-questions")
    public String fetchQuestions() {
        questionService.fetchAndStoreQuestions();
        return "Questions fetched and stored successfully!";
    }

    @GetMapping("/play")
    public QuestionDto getQuestion() {
        QuestionEntity question = questionService.getRandomQuestion();
        return new QuestionDto(question.getId(), question.getQuestion());
    }

    @PostMapping("/next")
    public NextQuestionResponse getNextQuestion(@RequestBody AnswerRequest answerRequest) {
        String correctAnswer = questionService.getCorrectAnswer(answerRequest.getQuestionId());
        QuestionEntity nextQuestion = questionService.getRandomQuestion();
        return new NextQuestionResponse(correctAnswer, new QuestionDto(nextQuestion.getId(), nextQuestion.getQuestion()));
    }
}
