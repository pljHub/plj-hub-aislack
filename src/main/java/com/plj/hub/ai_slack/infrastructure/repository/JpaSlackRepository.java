package com.plj.hub.ai_slack.infrastructure.repository;

import com.plj.hub.ai_slack.domain.model.Slack;
import com.plj.hub.ai_slack.domain.repository.SlackRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaSlackRepository  extends JpaRepository<Slack, UUID>, SlackRepository {
    Optional<Slack> findByIdAndDeletedAtIsNull(UUID slackId);
}
