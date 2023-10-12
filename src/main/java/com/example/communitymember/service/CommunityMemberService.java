package com.example.communitymember.service;

import com.example.communitymember.domain.entity.CommunityMember;
import com.example.communitymember.domain.request.CommunityMemberReqeust;
import com.example.communitymember.repository.CommunityMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityMemberService {
    private final CommunityMemberRepository communityMemberRepository;

    public void saveCommunityMember(CommunityMemberReqeust communityMemberReqeust) {

        communityMemberRepository.save(communityMemberReqeust.toEntity());
    }


    //커뮤니티 구성원은 인원수가 많을 것 같지 않아서 페이지 대신 리스트로 뽑음
    public List<CommunityMember> findAllByCommunityId(Long communityId){
        return communityMemberRepository.findAllByCommunityId(communityId);
    }

    public List<CommunityMember> findAllByMemberId(Long memberId){
        return communityMemberRepository.findAllByMemberId(memberId);
    }

    @Transactional
    //커뮤니티가 업데이트 돼면 중계테이블도 업데이트 되어야함
    public void updateCommunityInCommunityMember(CommunityMemberReqeust communityMemberReqeust, Long communityId){
        communityMemberRepository.updateCommunityInCommunityMember(communityMemberReqeust,communityId);
    };

    @Transactional
    //멤버가 업데이트 되면 중계테이블도 업데이트 되어야함
    public void updateMemberInCommunityMember(CommunityMemberReqeust communityMemberReqeust){
        communityMemberRepository.updateMemberInCommunityMember(communityMemberReqeust, communityMemberReqeust.getMemberId());
    };


    public void deleteCommunityMember(Long memberId, Long communityId){
        communityMemberRepository.deleteByMemberIdAndCommunityId(memberId, communityId);
    }

    @Transactional
    public void memberDeleteHandler(Long userId){
        CommunityMember communityMember = communityMemberRepository.findByMemberId(userId).get();
        communityMember.setIsValid(Boolean.FALSE);
    }

}
