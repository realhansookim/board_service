package com.realhansookim.notice_board.vo.admin;

import java.time.LocalDate;

import com.realhansookim.notice_board.entity.MemberInfoEntity;

import lombok.Data;

@Data
public class AdminInfoVO {
    private Integer member_no;
    private String member_id;
    private String member_pwd;
    private String member_name;
    private LocalDate member_birth;
    private Integer member_grade;
    private Integer member_status;

    public AdminInfoVO(MemberInfoEntity data){
        this.member_no = data.getMiSeq();
        this.member_id = data.getMiId();
        this.member_pwd = data.getMiPwd();
        this.member_name = data.getMiName();
        this.member_birth = data.getMiBirth();
        this.member_grade = data.getMiGrade();
        this.member_status = data.getMiStatus();
    }
}
