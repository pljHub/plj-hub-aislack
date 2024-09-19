package com.plj.hub.ai_slack.domain.repository;

import com.plj.hub.ai_slack.domain.model.Slack;

import java.util.Optional;
import java.util.UUID;

public interface SlackRepository {

    Optional<Slack> findByIdAndDeletedAtIsNull(UUID slackId);

    Slack save(Slack slack);
}
