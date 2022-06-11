package com.cjc.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.in.model.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document,Integer> {

}
