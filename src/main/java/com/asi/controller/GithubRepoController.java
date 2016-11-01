package com.asi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asi.model.Repository;
import com.asi.service.GithubRepoService;

@RestController
public class GithubRepoController {

	@Autowired
	private GithubRepoService githubRepoService;

	@RequestMapping(path="/top5repos/{username}")
	public List<Repository> getTop5ReposBySize(@PathVariable("username") String username) throws Exception{
		
		List<Repository> top5repos = githubRepoService.getTop5Repos(username);
		return top5repos;
	}
}
