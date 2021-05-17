package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeOneTest;

public class ManagerTaskDeleteTest extends AcmeOneTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/task/delete-task.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void updateTaskPositive(final int recordIndex, final String title1, final String title2) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title1);
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Delete");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title2);
		
		super.signOut();
	}

}
