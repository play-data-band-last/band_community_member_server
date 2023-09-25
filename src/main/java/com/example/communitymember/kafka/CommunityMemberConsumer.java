package com.example.communitymember.kafka;

import com.example.communitymember.domain.request.CommunityMemberReqeust;
import com.example.communitymember.service.CommunityMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityMemberConsumer {
    private final CommunityMemberService communityMemberService;
    @KafkaListener(topics = TopicConfig.communityMember)
    public void listen(CommunityMemberReqeust communityMemberReqeust) {
        communityMemberService.saveCommunityMember(communityMemberReqeust);
    }

    @KafkaListener(topics = TopicConfig.communityMemberUpdate)
    public void updateCommunityMember(CommunityMemberReqeust communityMemberReqeust) {
        System.out.println("멤버 수정 들오긴함");
        communityMemberService.updateMemberInCommunityMember(communityMemberReqeust);
    }

}
