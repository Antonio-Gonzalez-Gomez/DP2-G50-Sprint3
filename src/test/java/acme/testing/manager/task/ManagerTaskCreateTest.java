package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeOneTest;

public class ManagerTaskCreateTest extends AcmeOneTest {
	
	@ParameterizedTest
	@CsvFileSource(resources="/manager/task/create-task.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void createTaskPositive(final int recordIndex, final String title, final String description, final String link, final String start_date, final String ending_date, final String workload, final String finished, final String privacy, final String execution_period, final String manager_id) {
		super.signIn("manager2", "manager2");
		
		super.clickOnMenu("Manager", "New task");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("startDate", start_date);
		super.fillInputBoxIn("endingDate", ending_date);
		super.fillInputBoxIn("workload", workload);
		if(finished.equals("true")) super.fillInputBoxIn("finished", "true");
		if(finished.equals("false")) super.fillInputBoxIn("finished", "false");
		if(privacy.equals("true")) super.fillInputBoxIn("privacy", "true");
		if(privacy.equals("false")) super.fillInputBoxIn("privacy", "false");
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, description);
		super.checkColumnHasValue(recordIndex, 2, finished);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("startDate", start_date);
		super.checkInputBoxHasValue("endingDate", ending_date);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("finished", finished);
		super.checkInputBoxHasValue("privacy", privacy);
		
		super.signOut();
	}
	

}
