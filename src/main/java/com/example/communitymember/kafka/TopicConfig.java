package com.example.communitymember.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String communityMember = "communityMember";
    public final static String communityNotify = "communityNotify";
    public final static String communityNotify1 = "communityNotify1";
    public final static String communityNotify2 = "communityNotify2";
    public final static String communityMemberUpdate = "communityMemberUpdate";
    public final static String communityMemberDLT = "communityMember.DLT";

    @Bean
    public NewTopic topicDLT() {
        return new NewTopic(communityMemberDLT, 1, (short) 1);
    }

    @Bean
    public NewTopic topicNotify0() {
        return new NewTopic(communityNotify, 1, (short) 1);
    }

    @Bean
    public NewTopic topicNotify1() {
        return new NewTopic(communityNotify1, 1, (short) 1);
    }

    @Bean
    public NewTopic topicNotify2() {
        return new NewTopic(communityNotify2, 1, (short) 1);
    }

}
