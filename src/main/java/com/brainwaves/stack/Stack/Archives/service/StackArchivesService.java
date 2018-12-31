package com.brainwaves.stack.Stack.Archives.service;

import java.util.List;
import java.util.UUID;

import com.brainwaves.stack.Stack.Archives.dto.StackArchivesHistoryDto;

/**
 * StackArchivesService is a interface for StackArchivesServiceImpl Class.
 * 
 * @author Saravanan Perumal
 *
 */
public interface StackArchivesService {

	/**
	 * getAllStackArchivesList method used to return the list of stack history.
	 * 
	 * @return list of StackHistory
	 */
	List<StackArchivesHistoryDto> getAllStackArchivesList();

	/**
	 * getStockHistoryById method used to find the history for particular date.
	 * 
	 * @param uniqueId
	 * @return StackHistoryEntity
	 */
	StackArchivesHistoryDto getStockHistoryById(UUID uniqueId);

	/**
	 * searchStackByKeyword method used to search stack archives list by keword.
	 * 
	 * @param keyword
	 * @return
	 */
	List<StackArchivesHistoryDto> searchStackByKeyword(String keyword);

	/**
	 * insertData method used to read and insert a data from CSV files into
	 * DataBase.
	 */
	void insertData();

}
