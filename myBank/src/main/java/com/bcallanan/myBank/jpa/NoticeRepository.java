package com.bcallanan.myBank.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bcallanan.myBank.entity.Notice;

@Repository
public interface NoticeRepository extends CrudRepository< Notice, Integer > {
	
	@Query( value = "from Notice n where NOW() BETWEEN noticeBeginDate and noticeEndDate" )
	List<Notice> findAllActiveNotices(); 
}
