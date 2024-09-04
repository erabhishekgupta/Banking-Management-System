// TransactionRepository.java

package com.cetpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cetpa.entity.TransactionSummary;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionSummary, Long> {
	
	@Query("from TransactionSummary where accountno=:arg")
    List<TransactionSummary> findByAccountno(@Param("arg")int accountno);
}
