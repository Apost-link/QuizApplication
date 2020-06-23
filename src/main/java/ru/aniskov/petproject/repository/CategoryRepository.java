package ru.aniskov.petproject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.pojo.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
