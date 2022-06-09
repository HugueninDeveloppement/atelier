package fr.epsi.rennes.poec.vanessa.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epsi.rennes.poec.vanessa.conf.Constantes;
import fr.epsi.rennes.poec.vanessa.dao.ArticleDAO;
import fr.epsi.rennes.poec.vanessa.dao.IngredientDAO;
import fr.epsi.rennes.poec.vanessa.domain.Article;
import fr.epsi.rennes.poec.vanessa.domain.Ingredient;
import fr.epsi.rennes.poec.vanessa.exception.BusinessException;

@Service
public class ArticleService {
	
	private static final Logger logger = LogManager.getLogger(ArticleService.class);
	
	@Autowired
	private ArticleDAO articleDAO;
	
	@Autowired
	private IngredientDAO ingredientDAO;
	
	public List<Article> getArticles(){
		return articleDAO.getArticles();
	}
	
	public void addArticle (Article article) throws BusinessException {
		if (article.getCode() == null 
				|| article.getLabel() == null
				|| article.getPrix() == 0) {
			throw new BusinessException("article.add.mandatory.missing");
		}
		logger.trace("code : {}", article.getCode());
		if (article.getCode().length() > Constantes.FIELD_MAX_SIZE
				|| article.getLabel().length() > Constantes.FIELD_MAX_SIZE) {
			throw new BusinessException("article.add.field.oversize");
		}
		if(! article.getCode().matches("^[A-Z0-9]+$")) {
			throw new BusinessException("article.add.code.invalid");
		}
		
		if (article.getPrix()<0) {
			throw new BusinessException("article.add.prix.invalid");
		}
		articleDAO.addArticle(article);
	}
	
	public Article getArticleByCode(String articleCode) throws BusinessException {
		if(articleCode == null) {
			throw new BusinessException("article.get.code.missing");
		}
		if(! articleCode.matches("^[A-Z0-9]+$")) {
			throw new BusinessException("article.get.code.invalid");
		}
		return articleDAO.getArticleByCode(articleCode);
	}
	
	@Transactional
	public void addIngredientToArticle(int articleId, int ingredientId) {
		articleDAO.addIngredientToArticle(articleId, ingredientId);
		
		Article article = articleDAO.getArticleById(articleId);
		Ingredient ingredient = ingredientDAO.getIngredientById(ingredientId);
		
		double prix = article.getPrix() + ingredient.getPrix();
		articleDAO.updatePrixArticle(articleId, prix);
	}

}
