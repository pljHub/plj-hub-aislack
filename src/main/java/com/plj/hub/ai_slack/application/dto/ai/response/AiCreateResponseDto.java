package com.plj.hub.ai_slack.application.dto.ai.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiCreateResponseDto {
    private List<Candidate> candidates;
    private UsageMetadata usageMetadata;

    // Getters and setters

    @Data
    public static class Candidate {
        private Content content;
        private String finishReason;
        private int index;
        private List<SafetyRating> safetyRatings;

        // Getters and setters
    }

    @Data
    public static class Content {
        private List<Part> parts;
        private String role;

        // Getters and setters
    }

    @Data
    public static class Part {
        private String text;

        // Getters and setters
    }

    public static class SafetyRating {
        private String category;
        private String probability;

        // Getters and setters
    }

    public static class UsageMetadata {
        private int promptTokenCount;
        private int candidatesTokenCount;
        private int totalTokenCount;

        // Getters and setters
    }
}

