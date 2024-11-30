package data.specification;


import org.springframework.data.jpa.domain.Specification;

import banquemisr.challenge05.task_mangment.Domain.History;
import banquemisr.challenge05.task_mangment.Domain.Task;
import banquemisr.challenge05.task_mangment.Domain.User;
import banquemisr.challenge05.task_mangment.Domain.actionEnum;

public class HistorySpecification {


    public static Specification<History> hasTaskID(Long task_id) {
        return (root, query, criteriaBuilder) -> 
        task_id == null ? null : criteriaBuilder.equal(root.get("task_id"), task_id);
    }
    
    public static Specification<History> hasAction(actionEnum action) {
        return (root, query, criteriaBuilder) -> 
        action == null ? null : criteriaBuilder.equal(root.get("action"), action);
    }
    
    public static Specification<History> hasPerformiedBy(User user) {
        return (root, query, criteriaBuilder) -> 
        user == null ? null : criteriaBuilder.equal(root.get("user_id"), user.getId());
    }
}

