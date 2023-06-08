package com.driver.QAFetcher.Service;


import com.driver.QAFetcher.Entities.QuestionEntity;
import com.driver.QAFetcher.Repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionEntity getRandomQuestion() {
        List<QuestionEntity> questions = questionRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        return questions.get(randomIndex);
    }

    public String getCorrectAnswer(int questionId) {
        Optional<QuestionEntity> question = questionRepository.findById(questionId);
        return question != null ? question.get().getAnswer() : null;
    }
    public void fetchAndStoreQuestions() {
        String apiUrl = "https://jservice.io/api/random?count=5";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                QuestionEntity[] questions = objectMapper.readValue(conn.getInputStream(), QuestionEntity[].class);

                List<QuestionEntity> questionList = Arrays.asList(questions);
                questionRepository.saveAll(questionList);
            } else {
                System.out.println("Failed to fetch questions. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

