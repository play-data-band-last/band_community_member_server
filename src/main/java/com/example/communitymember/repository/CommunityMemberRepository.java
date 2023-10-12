package com.example.communitymember.repository;

import com.example.communitymember.domain.entity.CommunityMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommunityMemberRepository extends CustomCommunityMemberRepository, JpaRepository<CommunityMember, Long> {

    @Query("select c " +
            "from CommunityMember c " +
            "where c.isValid = True " +
            "and  c.communityId = :communityId " +
            "and c.isValid = true ")
    List<CommunityMember> findAllByCommunityId(@Param("communityId") Long communityId);

    @Query("select c " +
            "from CommunityMember c " +
            "where c.isValid = True " +
            "and  c.memberId = :memberId " +
            "and c.isValid = true ")
    List<CommunityMember> findAllByMemberId(@Param("memberId") Long memberId);

    Optional<CommunityMember> findByMemberId(Long userId);

    @Modifying
    @Query("DELETE from CommunityMember c " +
            "where c.communityId = :communityId and " +
            "c.memberId =:memberId ")
    void deleteByMemberIdAndCommunityId(@Param("memberId") Long memberId,@Param("communityId") Long communityId);
}
