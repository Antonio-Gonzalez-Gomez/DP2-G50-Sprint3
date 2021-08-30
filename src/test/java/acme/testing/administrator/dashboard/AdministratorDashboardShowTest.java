package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeOneTest;

public class AdministratorDashboardShowTest extends AcmeOneTest{
	
	@ParameterizedTest
	@CsvFileSource(resources="/administrator/dashboard/show-dashboard.csv", encoding = "utf-8", numLinesToSkip=1)
	@Order(10)
	public void showDashboard(final int recordIndex) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Dashboard");
		
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[1]/td[normalize-space(text()) = '63.71']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[2]/td[normalize-space(text()) = '11.88']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[3]/td[normalize-space(text()) = '50.00']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[4]/td[normalize-space(text()) = '74.00']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td[normalize-space(text()) = '7.71']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[6]/td[normalize-space(text()) = '1.67']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[7]/td[normalize-space(text()) = '5.00']"));
		super.checkExists(By.xpath("/html/body/div[2]/div/table/tbody/tr[8]/td[normalize-space(text()) = '10.00']"));
		super.checkExists(By.xpath("//*[@id=\"canvas1\"]"));
		super.checkExists(By.xpath("//*[@id=\"canvas2\"]"));
		
		super.signOut();
	}

}
