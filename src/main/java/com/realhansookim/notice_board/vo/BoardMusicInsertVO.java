package com.realhansookim.notice_board.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardMusicInsertVO {
  private String title;
  private String content;
  private LocalDate regDt;
}
