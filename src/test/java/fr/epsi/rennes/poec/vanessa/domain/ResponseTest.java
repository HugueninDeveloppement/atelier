package fr.epsi.rennes.poec.vanessa.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResponseTest {
	
	@Test
	public void succes () {
		// given
		Response<String> response = new Response<>(true);
		
		//when
		boolean isSucces = response.isSuccess();
		
		//then
		Assertions.assertTrue(isSucces);
	}
}
