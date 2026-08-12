package com.alnwick.MonolithDocker.Repository;

import com.alnwick.MonolithDocker.Model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
