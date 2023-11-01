package com.example.communitymember.kafka;

import com.example.communitymember.domain.request.CommunityMemberReqeust;
import com.example.communitymember.domain.request.NotifyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotifyProducer {
    private final KafkaTemplate<String, NotifyRequest> kafkaTemplate;

    public void send(CommunityMemberReqeust communitySearchRequest) {
//        String communityTopic = null;
//
//        long num = communitySearchRequest.getMemberId() % 3;
//
//        System.out.println(num);
//
//        if (num == 0) {
//            communityTopic = TopicConfig.communityNotify0;
//        } else if (num == 1) {
//            communityTopic = TopicConfig.communityNotify1;
//        } else {
//            communityTopic = TopicConfig.communityNotify2;
//        }

        NotifyRequest notifyRequest = NotifyRequest.builder()
                .memberId(communitySearchRequest.getMemberId())
                .communityId(communitySearchRequest.getCommunityId())
                .memberName(communitySearchRequest.getMemberName())
                .memberProfileImg(communitySearchRequest.getMemberImage())
                .isRead(false)
                .communityName(communitySearchRequest.getCommunityName())
                .currTime(LocalDateTime.now())
                .flag(1)
                .build();


        CompletableFuture<SendResult<String, NotifyRequest>> resultCompletableFuture = kafkaTemplate.send(TopicConfig.communityNotify0, notifyRequest);

        resultCompletableFuture.thenAccept(result -> {
            System.out.println("send After " + communitySearchRequest.getCommunityId() + " " + "offset : " + result.getRecordMetadata().offset());
        });


//        kafkaProducer.send(new ProducerRecord<>(communityTopic, communitySearchRequest), new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata metadata, Exception exception) {
//                log.info("Kafka 전송 성공: {}", metadata);
//
//                log.error("Kafka 전송 중 오류 발생: {}", exception);
//                // exception 에 대한 핸들링..
//            }
//        });
    }
}
