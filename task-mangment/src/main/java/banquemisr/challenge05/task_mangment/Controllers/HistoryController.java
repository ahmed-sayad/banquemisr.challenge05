package banquemisr.challenge05.task_mangment.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import banquemisr.challenge05.task_mangment.Domain.History;
import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.actionEnum;
import banquemisr.challenge05.task_mangment.Repo.HistoryRepo;
import banquemisr.challenge05.task_mangment.services.HistoryServices;
import banquemisr.challenge05.task_mangment.services.impl.HistoryServiceImpl;
import banquemisr.challenge05.task_mangment.services.impl.TaskServiceImpl;
import ch.qos.logback.core.joran.action.Action;
import jakarta.validation.Valid;



@RestController
@RequestMapping("history/")
public class HistoryController {
	

	
	private HistoryServices historyServices;
	public HistoryController(HistoryServices historyServices) {
		super();
		this.historyServices = historyServices;
	}
	
	
	@GetMapping("all")
	public ResponseEntity<List<History>> getHistories(){
		return ResponseEntity.ok(historyServices.findAllHistories());
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addHistory(@RequestBody @Valid History new_h){
		historyServices.addHistory(new_h.getTask(), new_h.getAction(),new_h.getUser());
		return ResponseEntity.ok("History added successfully to this task");
	}

	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> delete_history(@PathVariable Long id){
		historyServices.delHistory(id);
		return ResponseEntity.ok("History with id " + id + " is deleted successfully");
	}
	
	  @GetMapping("/search")
	    public List<History> searchEmployees(
	            @RequestParam(required = false) Long task_id,
	            @RequestParam(required = false) actionEnum action,
	            @RequestParam(required = false) String email) {
	        return  historyServices.searchHistories(task_id, action);
	    }  	
}


