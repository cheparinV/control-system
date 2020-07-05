package org.demo.project.controlsystem.repository;

import org.demo.project.controlsystem.repository.model.Pass;
import org.springframework.data.repository.CrudRepository;

public interface PassRepository extends CrudRepository<Pass, Long> {

    Pass findPassByUserId(Long userId);

}
