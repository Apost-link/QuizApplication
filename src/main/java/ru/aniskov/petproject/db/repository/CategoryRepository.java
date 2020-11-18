package ru.aniskov.petproject.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.db.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
