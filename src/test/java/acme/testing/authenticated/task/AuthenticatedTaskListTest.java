package acme.testing.authenticated.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeOneTest;

public class AuthenticatedTaskListTest extends AcmeOneTest{
	@ParameterizedTest
	@CsvFileSource(resources="/authenticated/task/list-task.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void listTask(final int recordIndex, final String title, final String description, final String link, final String startDate, final String endingDate, final String workload, final String finished, final String privacy, final String executionPeriod) {
		super.signIn("authenticated1", "authenticated1");
		super.clickOnMenu("Authenticated", "Task List");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endingDate);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endingDate", endingDate);
		super.checkInputBoxHasValue("fake_workload", workload);
		
		
		super.signOut();
	}
	
}
