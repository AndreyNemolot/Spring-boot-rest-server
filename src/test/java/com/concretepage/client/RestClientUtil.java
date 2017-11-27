package com.concretepage.client;

import java.net.URI;

import com.concretepage.entity.UserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClientUtil {
    public void getArticleByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/article/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<UserInfo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, UserInfo.class, 1);
        UserInfo article = responseEntity.getBody();
        System.out.println("Id:"+article.getId()+", Title:"+article.getLogin()
                 +", Category:"+article.getPassword());
    }
	public void getAllArticlesDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/articles";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<UserInfo[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, UserInfo[].class);
        UserInfo[] articles = responseEntity.getBody();
        for(UserInfo article : articles) {
              System.out.println("Id:"+article.getId()+", Title:"+article.getLogin()
                      +", Category: "+article.getPassword());
        }
    }
    public void addArticleDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/article";
	    UserInfo objArticle = new UserInfo();
	    objArticle.setLogin("Spring REST Security using Hibernate");
	    objArticle.setPassword("Spring");
        HttpEntity<UserInfo> requestEntity = new HttpEntity<UserInfo>(objArticle, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    public void updateArticleDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/article";
	    UserInfo objArticle = new UserInfo();
	    objArticle.setId(1);
	    objArticle.setLogin("Update:Java Concurrency");
	    objArticle.setPassword("Java");
        HttpEntity<UserInfo> requestEntity = new HttpEntity<UserInfo>(objArticle, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteArticleDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/user/article/{id}";
        HttpEntity<UserInfo> requestEntity = new HttpEntity<UserInfo>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RestClientUtil util = new RestClientUtil();
        //util.getArticleByIdDemo();
    	util.getAllArticlesDemo();
    	//util.addArticleDemo();
    	//util.updateArticleDemo();
    	//util.deleteArticleDemo();
    }    
}
