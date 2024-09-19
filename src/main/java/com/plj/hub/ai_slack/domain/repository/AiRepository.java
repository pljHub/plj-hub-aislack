package com.plj.hub.ai_slack.domain.repository;

import com.plj.hub.ai_slack.domain.model.Ai;

public interface AiRepository {
    Ai save(Ai ai);
}
