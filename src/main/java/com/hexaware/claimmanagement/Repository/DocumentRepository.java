package com.hexaware.claimmanagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.claimmanagement.Entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer>{
	
	@Query("select d from Document d where d.doc_id=?1")
	public Document getFileById(int doc_id);
}
