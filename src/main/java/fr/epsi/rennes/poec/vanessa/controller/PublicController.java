package fr.epsi.rennes.poec.vanessa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.epsi.rennes.poec.vanessa.domain.Article;
import fr.epsi.rennes.poec.vanessa.domain.Response;
import fr.epsi.rennes.poec.vanessa.domain.User;
import fr.epsi.rennes.poec.vanessa.exception.BusinessException;
import fr.epsi.rennes.poec.vanessa.service.ArticleService;
import fr.epsi.rennes.poec.vanessa.service.UserService;

@Controller
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService; 
	
	@PostMapping("/public/register")
	public String register(User user) throws BusinessException {
		userService.addUser(user);
		return "redirect:/login";
	}
	

	
	@GetMapping("/public/articles")
	@ResponseBody
	public Response<List<Article>> getArticles(){
		return new Response<>(articleService.getArticles());
	}

}