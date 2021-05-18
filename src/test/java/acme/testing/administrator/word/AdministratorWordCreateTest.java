package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeOneTest;

public class AdministratorWordCreateTest extends AcmeOneTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/word/create-positive-word.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void crateWordPositive(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Change filter words");
		
		super.clickOnSubmitButton("Create new");
		
		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Create new");
		
		super.clickOnMenu("Administrator", "Change filter words");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("word", word);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/word/create-negative-word.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void crateWordNegative(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Change filter words");
		
		super.clickOnSubmitButton("Create new");
		
		super.fillInputBoxIn("word", word);
		super.clickOnSubmitButton("Create new");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
