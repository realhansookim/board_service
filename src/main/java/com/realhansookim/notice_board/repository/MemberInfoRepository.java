package com.realhansookim.notice_board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.realhansookim.notice_board.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Integer>{
    
    public Integer countByMiId (String miId);
    public MemberInfoEntity findByMiIdAndMiPwd(String miId, String miPwd);
    public MemberInfoEntity findByMiId(String miId);
    Page<MemberInfoEntity> findByMiIdContains(String miId, Pageable pageable);
}
