package banquemisr.challenge05.task_mangment.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import banquemisr.challenge05.task_mangment.Domain.History;
import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Domain.actionEnum;
import banquemisr.challenge05.task_mangment.Repo.HistoryRepo;
import banquemisr.challenge05.task_mangment.services.HistoryServices;
import banquemisr.challenge05.task_mangment.services.UserService;
import data.specification.HistorySpecification;
import data.specification.TaskSpecification;


@Service
@Component
public class HistoryServiceImpl implements HistoryServices{
	
	@Autowired
	private final HistoryRepo historyRepo;
	@Autowired
	private final UserService userService;
	

	public HistoryServiceImpl(HistoryRepo historyRepo, UserService userService) {
		super();
		this.historyRepo = historyRepo;
		this.userService = userService;
	}

	
	@Override
	public List<History> findAllHistories() {
		return historyRepo.findAll();
	}


	@Override
	public void addHistory(Task task, actionEnum action, User user) {
		History newHistory = new History();
		newHistory.setTask(task);
		newHistory.setAction(action);
		newHistory.setUser(user);
		historyRepo.save(newHistory);
		
	}


	@Override
	public void delHistory(Long id) {
		historyRepo.findById(id).orElseThrow();
		historyRepo.deleteById(id);
		
	}
	

	@Override
	public List<History> searchHistories(Long task_id, actionEnum action) {
		// TODO Auto-generated method stub
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) auth.getPrincipal();
		User user = userService.findByEmail(email);
		 Specification<History> spec = Specification.where(HistorySpecification.hasPerformiedBy(user))
	                .and(HistorySpecification.hasTaskID(task_id))
	                .and(HistorySpecification.hasAction(action));
		 return historyRepo.findAll(spec);
	}	
}
