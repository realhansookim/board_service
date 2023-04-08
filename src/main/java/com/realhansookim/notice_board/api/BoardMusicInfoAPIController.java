package com.realhansookim.notice_board.api;

import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.realhansookim.notice_board.service.BoardMusicInfoService;
import com.realhansookim.notice_board.vo.BoardMusicInsertVO;
import com.realhansookim.notice_board.vo.BoardMusicResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/music")
@RequiredArgsConstructor
public class BoardMusicInfoAPIController {
    private final BoardMusicInfoService bmiService;
    
    @Operation(summary = "추가", description="추가합니다.")
    @PutMapping(value = "/insert", consumes= MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> putBoardMusicInfo(
        @Parameter(description = "formdata로 데이터를 입력합니다. (title: 제목, content: 내용, member: 멤버 번호)")
        BoardMusicInsertVO data, 
        @Parameter(description = "formdata로 파일을 입력합니다.")
        MultipartFile img){
        Map<String, Object> map = bmiService.addBoardMusicInfo(data, img);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }   

    @GetMapping("/list")
    public ResponseEntity<BoardMusicResponseVO> getArtistGroupList(
        @RequestParam @Nullable String keyword, 
        @PageableDefault(size = 10, sort = "bmiSeq", direction = Sort.Direction.DESC)
        Pageable pageable
        ){
        return new ResponseEntity<>(bmiService.getBoardMusicList(keyword, pageable), HttpStatus.OK);
    } 
}
