package com.brainwaves.stack.Stack.Archives.repository;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.brainwaves.stack.Stack.Archives.dto.StackArchivesHistoryDto;

public interface StackArchivesRepository extends JpaRepository<StackArchivesHistoryDto, UUID> {
	
	List<StackArchivesHistoryDto> findBySymbol(String symbol, Pageable pageable);

}
