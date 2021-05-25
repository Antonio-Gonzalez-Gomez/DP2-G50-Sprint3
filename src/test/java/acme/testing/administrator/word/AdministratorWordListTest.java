package acme.testing.administrator.word;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeOneTest;

public class AdministratorWordListTest extends AcmeOneTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/word/list-word.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void listWord(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Change filter words");
		
		super.checkColumnHasValue(recordIndex, 0, word);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("spamword", word);
		
		super.signOut();
	}

}
