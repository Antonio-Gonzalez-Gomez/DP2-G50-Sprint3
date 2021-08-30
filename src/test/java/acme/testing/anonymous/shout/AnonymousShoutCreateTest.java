package acme.testing.anonymous.shout;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeOneTest;

public class AnonymousShoutCreateTest extends AcmeOneTest{
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/shout/create-positive-shout.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void createPositiveShout(final int recordIndex, final String moment, final String author, final String text) {

		super.clickOnMenu("Anonymous", "Create Shouts");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Anonymous", "List Shouts");
		
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/shout/create-negative-shout.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void createNegativeShout(final int recordIndex, final String moment, final String author, final String text) {

		super.clickOnMenu("Anonymous", "Create Shouts");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.clickOnSubmitButton("Create");
		super.checkErrorsExist();
	}
}
