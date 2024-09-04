package com.cetpa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cetpa.entity.Account;
import com.cetpa.entity.TransactionSummary;

import jakarta.transaction.Transactional;


@Repository
public interface AccountRepository extends JpaRepository<Account , Integer> {

     		Account findByUserId(String userId);
	 
 
	 	    @Transactional
		    @Modifying
		    @Query("update Account set amount = amount + :amount where accountNo = :accountNo")
		    void updateAmount(@Param("amount") int amount, @Param("accountNo") int accountNo);


		

 


			void save(TransactionSummary transactionSummary);


	 	    


		
		



	
	
}
