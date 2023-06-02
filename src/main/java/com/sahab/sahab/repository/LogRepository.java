package com.sahab.sahab.repository;

import com.sahab.sahab.model.Log;
import com.sahab.sahab.model.Warning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface LogRepository extends CrudRepository<Warning, Integer> {
}
