package ru.project.task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.task_manager.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
