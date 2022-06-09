package fr.epsi.rennes.poec.vanessa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.rennes.poec.vanessa.domain.Article;
import fr.epsi.rennes.poec.vanessa.domain.Ingredient;
import fr.epsi.rennes.poec.vanessa.domain.Response;
import fr.epsi.rennes.poec.vanessa.exception.BusinessException;
import fr.epsi.rennes.poec.vanessa.service.ArticleService;
import fr.epsi.rennes.poec.vanessa.service.IngredientService;

@RestController
public class UserController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private IngredientService ingredientService;
	
	
	/**
	 * http://localhost:8080/user/article?code=123
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	
	@GetMapping("/user/article")
	public Response<Article> getArticleByCode(@RequestParam String code) throws BusinessException{
		return new Response<>(articleService.getArticleByCode(code));
	}
	
	@PostMapping("/user/article/ingredient")
	public Response<Void> addIngredientToArticle(
			@RequestParam int article_id,
			@RequestParam int ingredient_id ){
		articleService.addIngredientToArticle(article_id, ingredient_id);
		
		return new Response<>(true);
	}
	
	@GetMapping("/user/ingredients")
	public Response<List<Ingredient>> getIngredients() {
		return new Response<>(ingredientService.getIngredients());
	}

	
}
