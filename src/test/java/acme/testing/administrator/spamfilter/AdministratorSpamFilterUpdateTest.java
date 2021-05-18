package acme.testing.administrator.spamfilter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeOneTest;

public class AdministratorSpamFilterUpdateTest extends AcmeOneTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamfilter/update-positive-spamfilter.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void updateTaskPositive(final int recordIndex, final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Change filter threshold");
		
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Administrator", "Change filter threshold");
		
		super.checkInputBoxHasValue("threshold", threshold);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/spamfilter/update-negative-spamfilter.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void updateTaskNegative(final int recordIndex, final String threshold) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Change filter threshold");
		
		super.fillInputBoxIn("threshold", threshold);
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		
		super.signOut();
	}

}
