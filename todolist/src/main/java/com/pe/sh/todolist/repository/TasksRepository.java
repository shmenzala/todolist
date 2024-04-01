package com.pe.sh.todolist.repository;

import com.pe.sh.todolist.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shmen
 */
@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer>{
    
}
