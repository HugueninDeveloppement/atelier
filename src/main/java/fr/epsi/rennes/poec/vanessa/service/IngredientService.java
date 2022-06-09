package fr.epsi.rennes.poec.vanessa.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epsi.rennes.poec.vanessa.conf.Constantes;
import fr.epsi.rennes.poec.vanessa.dao.IngredientDAO;
import fr.epsi.rennes.poec.vanessa.domain.Ingredient;
import fr.epsi.rennes.poec.vanessa.exception.BusinessException;

@Service
public class IngredientService {
	
	private static final Logger logger = LogManager.getLogger(IngredientService.class);
	
	@Autowired
	private IngredientDAO ingredientDAO;
	
	public List<Ingredient> getIngredients() {
		return ingredientDAO.getIngredients();
	}
	
	public void addIngredient (Ingredient ingredient) throws BusinessException {
		if (ingredient.getCode() == null 
				|| ingredient.getLabel() == null
				|| ingredient.getPrix() == 0) {
			throw new BusinessException("ingredient.add.mandatory.missing");
		}
		logger.trace("code : {}", ingredient.getCode());
		if (ingredient.getCode().length() > Constantes.FIELD_MAX_SIZE
				|| ingredient.getLabel().length() > Constantes.FIELD_MAX_SIZE) {
			throw new BusinessException("ingredient.add.field.oversize");
		}
		if(! ingredient.getCode().matches("^[A-Z0-9]+$")) {
			throw new BusinessException("ingredient.add.code.invalid");
		}
		
		if (ingredient.getPrix()<0) {
			throw new BusinessException("ingredient.add.prix.invalid");
		}
		ingredientDAO.addIngredient(ingredient);
	}
	
	@Transactional
	public void deleteIngredient (int ingredientId) {
		ingredientDAO.deleteIngredient(ingredientId);
	}

}