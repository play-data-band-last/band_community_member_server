package com.example.communitymember.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "CommunityMembers",indexes = {
        @Index(name = "communityIdIndenx",columnList = "communityId"),
        @Index(name = "memberIdIndenx",columnList = "memberId")})
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long communityId;
    private Long memberId;
    private String communityName;
    private String communityImage;
    private String memberRole;
    private String memberName;
    private String memberImage;


}
