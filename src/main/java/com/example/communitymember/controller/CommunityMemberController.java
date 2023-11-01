package com.example.communitymember.controller;


import com.example.communitymember.domain.entity.CommunityMember;
import com.example.communitymember.domain.request.CommunityMemberReqeust;
import com.example.communitymember.service.CommunityMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/communitymember")
public class CommunityMemberController {
    private final CommunityMemberService communityMemberService;

    @PostMapping("/{communityId}")
    public void saveCommunityMember(@PathVariable("communityId") Long communityId,
                                    @RequestBody CommunityMemberReqeust communityMemberReqeust){
        communityMemberReqeust.setCommunityId(communityId);

        communityMemberService.saveCommunityMember(communityMemberReqeust);
    }

    @GetMapping("/communityid/{communityId}")
    public List<CommunityMember> findAllByCommunityId(@PathVariable("communityId") Long communityId){
        return communityMemberService.findAllByCommunityId(communityId);
    }

    @GetMapping("/memberid/{memberId}")
    public List<CommunityMember> findAllByMemberId(@PathVariable("memberId") Long memberId){
        return communityMemberService.findAllByMemberId(memberId);
    }


    @PutMapping("/updatecommunity/{communityId}")
    public void updateCommunityInCommunityMember(
            @RequestBody CommunityMemberReqeust communityMemberReqeust,
            @PathVariable("communityId") Long communityId){
        communityMemberService.updateCommunityInCommunityMember(communityMemberReqeust,communityId);
    }

    @PutMapping("/updatemember/{memberId}")
    public void updateMemberInCommunityMember(
            @RequestBody CommunityMemberReqeust communityMemberReqeust,
            @PathVariable("memberId") Long memberId){
        communityMemberReqeust.setMemberId(memberId);
        communityMemberService.updateMemberInCommunityMember(communityMemberReqeust);
    }


    @Transactional
    @DeleteMapping("/memberid/{memberId}/communityid/{communityId}")
    public void deleteCommunityMember(
            @PathVariable("memberId") Long memberId,
            @PathVariable("communityId")Long communityId){
        communityMemberService.deleteCommunityMember(memberId,communityId);
    }

    @GetMapping("/redisGet/{communityId}")
    public List<Long> findByCommunityIdFromRedis(@PathVariable String communityId) {
        return communityMemberService.findByCommunityIdFromRedis(communityId);
    }

    @DeleteMapping("/redisDel/{communityId}")
    public void deleteCommunity(@PathVariable String communityId) {

        communityMemberService.deleteCommunity(communityId);
    }
}
