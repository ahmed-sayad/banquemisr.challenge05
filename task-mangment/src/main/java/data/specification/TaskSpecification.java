package data.specification;
import banquemisr.challenge05.task_mangment.Domain.Task;

import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> 
        title == null ? null : criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Task> hasDescription(String description) {
        return (root, query, criteriaBuilder) -> 
        description == null ? null : criteriaBuilder.equal(root.get("description"), description);
    }
    
    public static Specification<Task> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> 
        status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }
    
    public static Specification<Task> hasPriority(String priority) {
        return (root, query, criteriaBuilder) -> 
        priority == null ? null : criteriaBuilder.equal(root.get("priority"), priority);
    }
    
    public static Specification<Task> hasDueDate(String due_date) {
        return (root, query, criteriaBuilder) -> 
        due_date == null ? null : criteriaBuilder.equal(root.get("due_date"), due_date);
    }

               
}

