package banquemisr.challenge05.task_mangment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import banquemisr.challenge05.task_mangment.Domain.History;
import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Domain.actionEnum;


@Service
public interface HistoryServices {
	
   List<History> findAllHistories();
	
	void addHistory(Task task, actionEnum action, User user);
	
	void delHistory(Long id);
	
	public List<History> searchHistories(Long task_id, actionEnum action);

}
