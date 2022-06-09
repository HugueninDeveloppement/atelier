package fr.epsi.rennes.poec.vanessa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.rennes.poec.vanessa.domain.Article;
import fr.epsi.rennes.poec.vanessa.domain.Ingredient;
import fr.epsi.rennes.poec.vanessa.domain.Response;
import fr.epsi.rennes.poec.vanessa.exception.BusinessException;
import fr.epsi.rennes.poec.vanessa.service.ArticleService;
import fr.epsi.rennes.poec.vanessa.service.IngredientService;


@RestController
public class AdminController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@PostMapping("/admin/article")
	public Response<Void> addArticle(@RequestBody Article article) throws BusinessException {
		articleService.addArticle(article);
		return new Response<>(true);
	}
	
	@PostMapping("/admin/ingredient")
	public Response<Void> addIngredient(@RequestBody Ingredient ingredient) throws BusinessException {
		ingredientService.addIngredient(ingredient);
		return new Response<>(true);
	}
	
	@GetMapping("/admin/ingredients")
	@ResponseBody
	public Response<List<Ingredient>> getIngredients(){
		return new Response<>(ingredientService.getIngredients());
	}
	
	@DeleteMapping("/admin/ingredient")
	public Response<Void> deleteIngredient(@RequestParam int id){
		ingredientService.deleteIngredient(id);
		return new Response<>(true);
		
	}
}
