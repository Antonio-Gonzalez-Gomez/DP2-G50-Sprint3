package acme.testing.anonymous.task;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.AcmeOneTest;

public class AnonymousTaskListTest extends AcmeOneTest {
	@ParameterizedTest
	@CsvFileSource(resources="/anonymous/task/list-task.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void listTask(final int recordIndex, final String title, final String description, final String link, final String startDate, final String endingDate, final String workload, final String finished, final String privacy, final String executionPeriod) {
		
		super.clickOnMenu("Anonymous", "List Tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startDate);
		super.checkColumnHasValue(recordIndex, 2, endingDate);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("endingDate", endingDate);
		super.checkInputBoxHasValue("workload", workload);
		
	}

	
	
}
