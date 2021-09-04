package acme.features.manager.task;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.administrator.spamFilter.AdministratorSpamFilterValidateService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	@Autowired
	private AdministratorSpamFilterValidateService filter;
	
	// AbstractListService<Employer, Job> -------------------------------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		boolean result;
		int taskId;
		Task task;
		Manager manager;
		Principal principal;

		taskId = request.getModel().getInteger("id");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		if (this.filter.validate(entity.getTitle()))
			errors.state(request, !this.filter.validate(entity.getTitle()), "title", "manager.task.form.error.title_spam");
		if (this.filter.validate(entity.getDescription()))
			errors.state(request, !this.filter.validate(entity.getDescription()), "description", "manager.task.form.error.description_spam");
		assert errors != null;
		if(entity.getStartDate() != null && entity.getEndingDate() != null) entity.setExecutionPeriod();
		
		final boolean finish = entity.getFinished();
		
		if (!errors.hasErrors("past_task") && entity.getStartDate() != null) {
			errors.state(request, (entity.getStartDate().after(Date.from(Instant.now())) || finish), "startDate", "manager.task.form.error.past_task");
		}
		
		if (!errors.hasErrors("incorrect_finish") && entity.getStartDate() != null && entity.getEndingDate() != null) {
			errors.state(request, entity.getEndingDate().after(entity.getStartDate()), "endingDate", "manager.task.form.error.incorrect_finish");
		}
		
		//Validación de workload previo y asignación de workload real
		final Double false_workload = entity.getFake_workload();
		if(false_workload!=null) {
			if (false_workload <= 0) {
				errors.state(request, false_workload > 0, "fake_workload", "manager.task.form.error.negative");
			}
			final BigDecimal bd = new BigDecimal(String.valueOf(false_workload));
			final BigDecimal decimals = bd.subtract(new BigDecimal(bd.intValue()));
			if(!errors.hasErrors("incorrect_decimals")) {
				errors.state(request, (decimals.precision()<=2), "fake_workload", "manager.task.form.error.incorrect_decimals");
			}
			if(!errors.hasErrors("over_60")) {
				final BigDecimal limit = BigDecimal.valueOf(0.6);
				errors.state(request, (decimals.compareTo(limit)<=0), "fake_workload", "manager.task.form.error.over_60");
			}
			final Double real_decimals = decimals.doubleValue()/0.6;
			final Double real_workload = false_workload.intValue() + real_decimals;
			entity.setWorkload(real_workload);
			entity.setFake_workload(false_workload);
		}
		
		//Validación de workload real
		if (!errors.hasErrors("big_workload") && entity.getWorkload() != null) {
			errors.state(request, (entity.getWorkload()<=99.99), "fake_workload", "manager.task.form.error.big_workload");
		}
		
		if (!errors.hasErrors("work_overload") && entity.getExecutionPeriod() != null && entity.getWorkload() != null) {
			errors.state(request, entity.getWorkload() < entity.getExecutionPeriod(), "fake_workload", "manager.task.form.error.work_overload");
		}
		
		
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;


		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		if(entity.getExecutionPeriod()==null && entity.getStartDate()!=null) entity.setExecutionPeriod();
		
		request.unbind(entity, model, "title", "description", "link", "startDate");
		request.unbind(entity, model, "endingDate", "workload", "fake_workload", "finished", "privacy", "executionPeriod");
		model.setAttribute("readonly", false);
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneTaskById(id);

		return result;
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
	
		this.repository.save(entity);
	}
}
