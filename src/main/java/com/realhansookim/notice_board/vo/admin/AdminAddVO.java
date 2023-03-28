package com.realhansookim.notice_board.vo.admin;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminAddVO {
  private String id;
  private String pwd;
  private String name;
  private LocalDate birth;
  private Integer type;
  
}
