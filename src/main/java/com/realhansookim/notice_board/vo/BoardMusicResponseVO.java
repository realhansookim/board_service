package com.realhansookim.notice_board.vo;

import java.util.List;

import com.realhansookim.notice_board.entity.BoardMusicInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardMusicResponseVO {
        private List<BoardMusicInfoEntity> list;
        // @Schema(description = "총 아티스트 그룹 수", example = "121")
        private Long total;
        // @Schema(description = "총 페이지 수", example = "13")
        private Integer totalPage;
        // @Schema(description = "조회한 페이지 (1 페이지 -> 0 / 2 페이지 -> 1)")
        private Integer currentPage;
    }

