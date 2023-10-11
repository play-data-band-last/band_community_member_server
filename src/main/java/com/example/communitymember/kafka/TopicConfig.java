package com.example.communitymember.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String communityMember = "communityMember";

    public final static String communityMemberDLT = "communityMember.DLT";

    public final static String memberUpdate = "memberUpdate";

    public final static String memberUpdateDLT = "memberUpdate.DLT";


    @Bean
    public NewTopic topicDLT() {
        return new NewTopic(communityMemberDLT, 1, (short) 1);
    }

    @Bean
    public NewTopic memberUpdateDLT() {
        return new NewTopic(memberUpdateDLT, 1, (short) 1);
    }


}
