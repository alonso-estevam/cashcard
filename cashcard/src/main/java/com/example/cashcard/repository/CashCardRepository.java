package com.example.cashcard.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.cashcard.model.CashCard;

public interface CashCardRepository extends CrudRepository<CashCard, Long> {

}
