package com.realhansookim.notice_board.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.realhansookim.notice_board.repository.MemberInfoRepository;
import com.realhansookim.notice_board.entity.BoardMusicInfoEntity;
import com.realhansookim.notice_board.repository.BoardMusicInfoRepository;
import com.realhansookim.notice_board.vo.BoardMusicInsertVO;
import com.realhansookim.notice_board.vo.BoardMusicResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 의존성 자동 주입 - @Autowired 자동으로 해줌
public class BoardMusicInfoService {
    private final FileService fileService;
    private final BoardMusicInfoRepository bmiRepo;
    private final MemberInfoRepository mRepo;

    public Map<String, Object> addBoardMusicInfo(BoardMusicInsertVO data, MultipartFile img){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String savedFilePath = "";
        try{
            savedFilePath = fileService.saveImageFile("music", img);
        }
        catch(Exception e){
            System.out.println("파일 전송 실패");
            resultMap.put("status", false);
            resultMap.put("message", "파일 전송에 실패했습니다.");
            resultMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        BoardMusicInfoEntity entity = BoardMusicInfoEntity.builder()
        .bmiTitle(data.getTitle())
        .bmiContent(data.getContent())
        .bmiRegDt(data.getRegDt())
        .bmiImg(savedFilePath)
        .build();
        bmiRepo.save(entity);

        resultMap.put("status", true);
        resultMap.put("message", "아티스트 그룹이 추가되었습니다.");
        resultMap.put("code", HttpStatus.OK);   

        return resultMap;
    }

    public BoardMusicResponseVO getBoardMusicList(String keyword, Pageable pageable){
        if(keyword == null) keyword = "";
        Page<BoardMusicInfoEntity> bmiPage = bmiRepo.findByBmiTitleContains(keyword, pageable);
        BoardMusicResponseVO response = BoardMusicResponseVO.builder()
            .list(bmiPage.getContent())
            .total(bmiPage.getTotalElements())
            .totalPage(bmiPage.getTotalPages())
            .currentPage(bmiPage.getNumber())
            .build();
        return response;   
    }
}