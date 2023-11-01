package com.example.communitymember.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class NotifyRequest {
    private long communityId;
    private long memberId;
    private String memberName;
    private String communityName;
    private String memberProfileImg;
    private String message;
    private LocalDateTime currTime;
    //  1 : 커뮤니티 가입 공지
    private Integer flag;

    // 예약 발송용 필드
    private LocalDateTime scheduledTime;
    private boolean isRead;
}
