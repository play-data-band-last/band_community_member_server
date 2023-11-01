package com.example.communitymember.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Getter
@AllArgsConstructor
@RedisHash(value = "communityList")
public class CommunityMemberRedis {

    @Id
    private String id;

    private List<Long> userList;

}