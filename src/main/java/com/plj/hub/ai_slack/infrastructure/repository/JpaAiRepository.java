package com.plj.hub.ai_slack.infrastructure.repository;

import com.plj.hub.ai_slack.domain.model.Ai;
import com.plj.hub.ai_slack.domain.repository.AiRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAiRepository extends JpaRepository<Ai, UUID>, AiRepository {

}
