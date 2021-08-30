package acme.testing.anonymous.shout;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeOneTest;

public class AnonymousShoutListTest extends AcmeOneTest{
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/shout/list-shout.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void listShout(final int recordIndex, final String moment, final String author, final String text) {

		super.clickOnMenu("Anonymous", "List Shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		
	}
}


