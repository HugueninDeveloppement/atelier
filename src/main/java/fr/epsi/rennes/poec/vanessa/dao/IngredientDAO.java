package fr.epsi.rennes.poec.vanessa.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import fr.epsi.rennes.poec.vanessa.domain.Ingredient;
import fr.epsi.rennes.poec.vanessa.exception.TechnicalException;

@Repository
public class IngredientDAO {
	
	@Autowired
	private DataSource ds;
	
	
	public List<Ingredient> getIngredients() {
		String sql = "select * from ingredient";
		try {
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			List<Ingredient> result = new ArrayList<>();
			while (rs.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setId(rs.getInt("id"));
				ingredient.setCode(rs.getString("code"));
				ingredient.setLabel(rs.getString("label"));
				ingredient.setPrix(rs.getDouble("prix"));
				
				result.add(ingredient);
			}
			return result;
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
	}
	
	public void addIngredient(Ingredient ingredient) {
		String sql = String.format(
				"insert into ingredient (code, label, prix) values ('%s', '%s', '%s')",
				ingredient.getCode(), ingredient.getLabel(), ingredient.getPrix());
		
		try {
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
	}
	
	public void deleteIngredient(int ingredientId) {
		String sql1 = String.format(
				"DELETE FROM ingredient WHERE id= %s",
				ingredientId);
		
		try {
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql1);
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
		String sql2 = String.format(
				"DELETE FROM article_ingredient WHERE ingredient_id= %s",
				ingredientId);
		
		try {
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
		
		
		
	}
	
	public Ingredient getIngredientById(int ingredientId) {
		String sql = String.format("SELECT * FROM ingredient where id = %s", ingredientId);
		
		try {
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setId(rs.getInt("id"));
				ingredient.setCode(rs.getString("code"));
				ingredient.setLabel(rs.getString("label"));
				ingredient.setPrix(rs.getDouble("prix"));
				
				return ingredient;
			}
			return null;
		} catch (SQLException e) {
			throw new TechnicalException(e);
		}
	}

}
