package com.realhansookim.notice_board.entity;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "board_music_info")
public class BoardMusicInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bmi_seq") private Integer bmiSeq;
    @Column(name = "bmi_title") private String bmiTitle;
    @Column(name = "bmi_content") private String bmiContent;
    @Column(name = "bmi_reg_dt") private LocalDate bmiRegDt;
    // @Column(name = "bmi_mi_seq") private Integer bmiMiSeq;
    // @ManyToOne @JoinColumn(name = "bmi_mi_seq") private MemberInfoEntity memberInfo;
    @Column(name = "bmi_img") private String bmiImg;    
}
