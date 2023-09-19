package com.example.communitymember.repository;

import com.example.communitymember.domain.request.CommunityMemberReqeust;

public interface CustomCommunityMemberRepository {
    void updateCommunityInCommunityMember(CommunityMemberReqeust communityMemberReqeust, Long communityId);

    void updateMemberInCommunityMember(CommunityMemberReqeust communityMemberReqeust, Long memberID);

}
