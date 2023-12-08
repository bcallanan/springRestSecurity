package com.bcallanan.myBank.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcallanan.myBank.entity.Notice;
import com.bcallanan.myBank.jpa.NoticeRepository;

@RestController
public class NoticeController {

	@Autowired
	NoticeRepository noticeRepository;
	
	@GetMapping( value = { "/Notices", "/notices" })
	public ResponseEntity<List<Notice>> getNotices() {
	
		List< Notice > notices = noticeRepository.findAllActiveNotices();
		if ( notices != null ) {
			return ResponseEntity.ok()
					.cacheControl( CacheControl.maxAge( 60, TimeUnit.SECONDS))
					.body( notices );
		}
		return null;
	}
}
