package com.example.communitymember.kafka;

import com.example.communitymember.common.RestError;
import com.example.communitymember.common.RestResult;
import com.example.communitymember.domain.request.CommunityMemberReqeust;
import com.example.communitymember.service.CommunityMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityMemberConsumer {
    private final CommunityMemberService communityMemberService;
    @RetryableTopic
    @KafkaListener(topics = TopicConfig.communityMember)
    public void listen(CommunityMemberReqeust communityMemberReqeust) {
        System.out.println("consumer : " + communityMemberReqeust);
//        throw new IllegalArgumentException("test");

        communityMemberService.saveCommunityMember(communityMemberReqeust);
    }

    @RetryableTopic
    @KafkaListener(topics = TopicConfig.memberUpdate)
    public void updateCommunityMember(CommunityMemberReqeust communityMemberReqeust) {

        communityMemberService.updateMemberInCommunityMember(communityMemberReqeust);
    }

    @RetryableTopic
    @KafkaListener(topics = TopicConfig.memberDelete)
    public void memberDeleteListener(CommunityMemberReqeust communityMemberReqeust) {
        communityMemberService.memberDeleteHandler(communityMemberReqeust.getMemberId());
    }


    @DltHandler
    public void processDltMessage(String dltMessage) {
        // DLT 토픽에서 메시지를 처리합니다. (예: 로깅 또는 추가 조사)
        System.out.println("DLT에서 메시지 수신: " + dltMessage);
    }



}
