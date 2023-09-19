package com.example.communitymember.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String memberImage;
    private String memberName;

}
