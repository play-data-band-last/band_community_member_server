package com.example.communitymember.service;

import com.example.communitymember.domain.entity.CommunityMember;
import com.example.communitymember.domain.request.CommunityMemberReqeust;
import com.example.communitymember.kafka.NotifyProducer;
import com.example.communitymember.repository.CommunityMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityMemberService {
    private final CommunityMemberRepository communityMemberRepository;
    private final NotifyProducer notifyProducer;
    private final RedisTemplate<String, Object> redisTemplate;
    public void saveCommunityMember(CommunityMemberReqeust communityMemberReqeust) {

        System.out.println(communityMemberReqeust.getMemberRole());
        // 분산트랜잭션이 필요함..
        try {
            communityMemberRepository.save(communityMemberReqeust.toEntity());
        }catch (Exception e) {
            e.printStackTrace();
        }

//        // redis save..
//        redisSaveData(communityMemberReqeust);

        // kafka producer
        try {
            notifyProducer.send(communityMemberReqeust);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void redisSaveData(CommunityMemberReqeust communityMemberReqeust) {
        String communityId = String.valueOf(communityMemberReqeust.getCommunityId());

        // 현재 redis에 저장된 게 있으면 반환 없으면 빈 배열 반환
        List<Long> existingList = findByCommunityIdFromRedis(communityId);
        existingList.add(communityMemberReqeust.getMemberId());

        System.out.println("community-" + communityId);
        System.out.println(existingList);
        redisTemplate.opsForHash().put("community-" + communityId, "communityList", existingList);
    }


    public List<Long> findByCommunityIdFromRedis(String communityId) {
        HashOperations<String, String, List<Long>> hashOps = redisTemplate.opsForHash();
        List<Long> existingList = hashOps.get("community-"+communityId, "communityList");
        return existingList != null ? existingList : new ArrayList<>();
    }

    public void deleteCommunity(String communityId) {
        String key = "community-" + communityId;
        HashOperations<String, String, List<Long>> hashOperations = redisTemplate.opsForHash();

        // Redis에서 데이터 삭제
        redisTemplate.delete(key);
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

}
