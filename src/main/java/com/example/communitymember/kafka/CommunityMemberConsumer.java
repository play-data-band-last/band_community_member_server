package com.example.communitymember.kafka;

import com.example.communitymember.common.RestError;
import com.example.communitymember.common.RestResult;
import com.example.communitymember.domain.request.CommunityMemberReqeust;
import com.example.communitymember.service.CommunityMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityMemberConsumer {
    private final CommunityMemberService communityMemberService;
    @KafkaListener(topics = TopicConfig.communityMember)
    public void listen(CommunityMemberReqeust communityMemberReqeust) {
        System.out.println("consumer : " + communityMemberReqeust);
//        throw new IllegalArgumentException("test");

        communityMemberService.saveCommunityMember(communityMemberReqeust);
    }

    @KafkaListener(topics = TopicConfig.communityMemberDLT)
    public void dltListen(byte[] in) {
        log.info("dlt : " + new String(in));
    }

    @KafkaListener(topics = TopicConfig.communityMemberUpdate)
    public void updateCommunityMember(CommunityMemberReqeust communityMemberReqeust) {

        communityMemberService.updateMemberInCommunityMember(communityMemberReqeust);
    }



}
