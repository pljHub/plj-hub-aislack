package com.plj.hub.ai_slack.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "p_ais")
@Getter
@NoArgsConstructor
public class Ai extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "text")
    private String question;

    @Column(nullable = false, columnDefinition = "text")
    private String answer;

    public static Ai create(Long userId, String question, String answer) {
        Ai ai = new Ai();
        ai.create(userId);
        ai.question = question;
        ai.answer = answer;
        return ai;
    }


    public static Ai create(String question, String answer) {
        Ai ai = new Ai();
        ai.question = question;
        ai.answer = answer;
        return ai;
    }

    public void deleteAi(Long userId) {
        delete(userId);
    }
}
