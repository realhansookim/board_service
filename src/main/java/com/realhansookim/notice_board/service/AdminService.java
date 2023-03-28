package com.realhansookim.notice_board.service;

import java.lang.reflect.Member;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.realhansookim.notice_board.entity.MemberInfoEntity;
import com.realhansookim.notice_board.repository.MemberInfoRepository;
import com.realhansookim.notice_board.vo.admin.AdminAddVO;
import com.realhansookim.notice_board.vo.admin.AdminInfoVO;
import com.realhansookim.notice_board.vo.admin.AdminLoginVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
  private final MemberInfoRepository mRepo;
  public Map<String,Object> addAdmin(AdminAddVO data){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    if(data.getId() == null || data.getId().equals("")){
      map.put("status",false);
      map.put("message","아이디를 입력하세요");
    }
    else if(mRepo.countByMiId(data.getId()) != 0){
      map.put("status", false);
      map.put("message", data.getId()+"은/는 이미 사용중인 아이디 입니다.");
    }
    else if(data.getPwd() == null || data.getPwd().equals("")){
      map.put("status",false);
      map.put("message","비밀번호를 입력하세요");
    }
    else{
      MemberInfoEntity entity = MemberInfoEntity.builder().miId(data.getId()).miPwd(data.getPwd()).miName(data.getName())
      .miBirth(data.getBirth()).miGrade(data.getType()).build();
      mRepo.save(entity);
      map.put("status", true);
      map.put("message", "계정 신청 완료");
    }
    return map;
  }

  public Map<String,Object> loginAdmin(AdminLoginVO login){
    Map<String,Object> map = new LinkedHashMap<String,Object>();
    MemberInfoEntity entity = mRepo.findByMiIdAndMiPwd(login.getAdmin_id(), login.getAdmin_pwd());
    if(entity == null){
      map.put("status", false);
      map.put("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
    }
    else if(entity.getMiStatus() == 0){
      map.put("status",false);
      map.put("message", "등록대기 중인 계정입니다.");
    }
    else if(entity.getMiStatus() == 2){
      map.put("status",false);
      map.put("message", "이용 정지된 계정입니다.");
    }
    else if(entity.getMiStatus() == 3){
      map.put("status",false);
      map.put("message", "이미 탈퇴한 계정입니다.");
    }
    else{
      map.put("status", true);
      map.put("message", "정상적으로 로그인 되었습니다.");
      map.put("login", new AdminInfoVO(entity));
    }
    return map;
  }
}
