package com.realhansookim.notice_board.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realhansookim.notice_board.service.BoardMusicInfoService;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MusicComtroller {
  private final BoardMusicInfoService boardMusicInfoService;
  @GetMapping("/music/list")
  public String getMusicList(Model model, @RequestParam @Nullable String keyword,
  @PageableDefault(size = 10, sort="bmiSeq",direction = Sort.Direction.DESC)Pageable pageable
  ,HttpSession session){
    if(keyword == null){
      keyword = "";  
    }
    model.addAttribute("result", boardMusicInfoService.getBoardMusicList(keyword, pageable));
    model.addAttribute("keyword", keyword);
    return "/music/list";
  }
}
