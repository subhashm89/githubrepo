package com.asi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asi.model.Repository;

@Service
public class GithubRepoService {

	@Autowired
	private RestTemplate restTemplate;

	public List<Repository> getTop5Repos(String username) throws Exception {
		String url = "https://api.github.com/users/"+ username +"/repos";
		ResponseEntity<List<Repository>> response = restTemplate.exchange(url,HttpMethod.GET, null, new ParameterizedTypeReference<List<Repository>>(){
		});
		if(response.getStatusCode().is2xxSuccessful()){
			List<Repository> repos = response.getBody();
			repos.sort((repo1, repo2) -> repo2.getSize().compareTo(repo1.getSize()));
			
			List<Repository> top5repos = repos.stream().filter(repo-> !repo.getIsPrivate()).limit(5).collect(Collectors.toList());
			return top5repos;
		}else{
			throw new Exception(response.getStatusCode().toString());
		}
	}
}
