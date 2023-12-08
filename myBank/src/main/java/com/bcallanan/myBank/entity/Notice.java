package com.bcallanan.myBank.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table( name = "notice_details" )
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int noticeId;
	
	private String noticeSummary;
	private String noticeDetails;
	
	private Date noticeBeginDate;
	private Date noticeEndDate;
	private Date updateDate;
	private Date createDate;
}
