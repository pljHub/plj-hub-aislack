package com.plj.hub.ai_slack.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "p_slacks")
@Getter
@NoArgsConstructor
public class Slack extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, columnDefinition = "text")
    private String message;

    public static Slack create(Long userId, String message) {
        Slack slack = new Slack();
        slack.message = message;
        slack.create(userId);
        return slack;
    }

    public static Slack create(String message) {
        Slack slack = new Slack();
        slack.message = message;
        return slack;
    }

    public void deleteSlack(Long userId) {
        delete(userId);
    }
}
