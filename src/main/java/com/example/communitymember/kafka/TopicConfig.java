package com.example.communitymember.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String communityMember = "communityMember";
    public final static String communityMemberUpdate = "communityMemberUpdate";

    public final static String communityMemberDLT = "communityMember.DLT";

    @Bean
    public NewTopic topicDLT() {
        return new NewTopic(communityMemberDLT, 1, (short) 1);
    }

}
