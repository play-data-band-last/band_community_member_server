package com.example.communitymember.domain.request;

import com.example.communitymember.domain.entity.CommunityMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunityMemberReqeust {
    private Long memberId;
    private String memberRole;
    private String memberName;
    private String memberImage;
    private String communityName;
    private String communityImage;


    public CommunityMember toEntity(Long communityId){
        System.out.println(communityId);
        return CommunityMember
                .builder()
                .memberId(memberId)
                .communityId(communityId)
                .memberRole(memberRole)
                .memberImage(memberImage)
                .memberName(memberName)
                .communityImage(communityImage)
                .communityName(communityName)
                .build();
    }

}


