package com.realhansookim.notice_board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.realhansookim.notice_board.entity.BoardMusicInfoEntity;

public interface BoardMusicInfoRepository extends JpaRepository<BoardMusicInfoEntity,Integer> {
Page<BoardMusicInfoEntity> findByBmiTitleContains(String bmiTitle, Pageable pageable);
}
