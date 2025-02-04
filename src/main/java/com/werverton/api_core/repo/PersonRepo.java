package com.werverton.api_core.repo;

import com.werverton.api_core.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PersonRepo  extends JpaRepository<Person, Long> {
}
