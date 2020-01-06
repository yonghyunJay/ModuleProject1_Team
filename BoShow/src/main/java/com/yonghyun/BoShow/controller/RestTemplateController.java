package com.yonghyun.BoShow.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {
	@GetMapping("getYoutube")
	public Map<String, Object> getYoutube(@RequestParam("youtubeTopic") String youtubeTopic) {
		RestTemplate rt = new RestTemplate();
		Map<String, Object> map = 
					rt.getForObject("https://www.googleapis.com/youtube/v3/search?part=snippet&key=AIzaSyDh6iWJUUbTzDfrki-eoXbGzfCDo4XbJqM&q=" + youtubeTopic, Map.class);

		return map;
	}
	
	@GetMapping("getKakaoVideo")
	public ResponseEntity<Map> KakaoVideo(@RequestParam("videoTopic") String videoTopic) {
		RestTemplate rt = new RestTemplate();
		RequestEntity requestEntity = null;
		try {
			requestEntity = RequestEntity
					.get(new URI("https://dapi.kakao.com/v2/search/vclip?query=" + URLEncoder.encode(videoTopic, "utf-8") + "&sort=recency&size=20"))
					.header("Authorization", "KakaoAK 7e6ebaf4ae41d1259b2ab492fc603254").build();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);
		return entity;
	}
	
	@GetMapping("getKakaoBlog")
	public ResponseEntity<Map> KakaoBlog(@RequestParam("blogTopic") String blogTopic) {
		RestTemplate rt = new RestTemplate();
		RequestEntity requestEntity = null;
		try {
			requestEntity = RequestEntity
					.get(new URI("https://dapi.kakao.com/v2/search/blog?query=" + URLEncoder.encode(blogTopic, "utf-8")))
					.header("Authorization", "KakaoAK 7e6ebaf4ae41d1259b2ab492fc603254").build();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);
		return entity;
	}
}
