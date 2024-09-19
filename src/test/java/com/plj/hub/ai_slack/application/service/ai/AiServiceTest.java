package com.plj.hub.ai_slack.application.service.ai;

import com.plj.hub.ai_slack.application.dto.ai.response.AiCreateResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootTest
class AiServiceTest {

    @Autowired WebClient webClient;
    @Autowired
    AiService aiService;

//    @Transactional
//    @Test
//    void createAi() {
//        String answer = aiService.create( "배송 정보 ");
//        System.out.println("responseText = " + answer);
//    }
}