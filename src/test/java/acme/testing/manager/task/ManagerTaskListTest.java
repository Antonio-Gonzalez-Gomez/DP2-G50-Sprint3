package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeOneTest;

public class ManagerTaskListTest extends AcmeOneTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/task/list-task.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void listTask(final int recordIndex, final String title, final String description, final String link, final String startDate, final String endingDate, final String workload, final String finished, final String privacy, final String executionPeriod) {
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, finished);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endingDate", endingDate);
		super.checkInputBoxHasValue("fake_workload", workload);
		super.checkInputBoxHasValue("finished", finished);
		super.checkInputBoxHasValue("privacy", privacy);
		
		super.signOut();
	}

}
