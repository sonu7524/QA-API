package com.driver.QAFetcher.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "QATable")
public class QuestionEntity {
    @Id
    private Integer id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;
}
