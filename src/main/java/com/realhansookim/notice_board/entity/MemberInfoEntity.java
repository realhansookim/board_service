package com.realhansookim.notice_board.entity;

import java.time.LocalDate;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DynamicInsert
@Table(name = "member_info")
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "mi_seq") private  Integer miSeq;
@Column(name = "mi_id") private String miId;
@Column(name = "mi_pwd") private String miPwd;
@Column(name = "mi_name") private String miName;
@Column(name = "mi_birth")  private LocalDate miBirth;
@Column(name = "mi_reg_dt") @ColumnDefault(value = "current_timestamp") private LocalDate miRegDt;
@Column(name = "mi_grade") @ColumnDefault("1") private Integer miGrade;
@Column(name = "mi_status") @ColumnDefault("1") private Integer miStatus;   
}
