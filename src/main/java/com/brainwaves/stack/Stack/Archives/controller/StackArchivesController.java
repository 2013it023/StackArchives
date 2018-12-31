package com.brainwaves.stack.Stack.Archives.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brainwaves.stack.Stack.Archives.dto.StackArchivesHistoryDto;
import com.brainwaves.stack.Stack.Archives.service.StackArchivesService;

/**
 * StackArchivesController is a API Class, all the client call's will be
 * received in this controller.
 * 
 * @author Saravanan Perumal
 *
 */
@RestController
@RequestMapping("stack/archives/history")
public class StackArchivesController {

	/**
	 * The stackArchivesService, which is used to invoke the business logic's for
	 * each API's.
	 */
	@Autowired
	private StackArchivesService stackArchivesService;

	/**
	 * getAllStackArchives API used to get and return all the stock archived history
	 * details.
	 * 
	 * @return list of StackArchivesHistoryDto
	 */
	@RequestMapping(value = "/getAllStackArchives", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	@CrossOrigin
	public List<StackArchivesHistoryDto> getAllStackArchives() {
		return stackArchivesService.getAllStackArchivesList();
	}

	/**
	 * getStackArchives API used to get and return a single StockArchived Record
	 * basedupon the uniqueID
	 * 
	 * @param uniqueId
	 * @return StackArchivesHistoryDto
	 */
	@RequestMapping(value = "/getStackArchives", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@CrossOrigin
	@ResponseBody
	public StackArchivesHistoryDto getStackArchives(@RequestParam UUID uniqueId) {
		return stackArchivesService.getStockHistoryById(uniqueId);
	}

	/**
	 * searchStackByKeyword API used to get and return a stock Archived record based
	 * upon the keyword.
	 * 
	 * @param keyword
	 * @return list of StackArchivesHistoryDto
	 */
	@RequestMapping(value = "/searchStackByKeyword", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	@CrossOrigin
	public List<StackArchivesHistoryDto> searchStackByKeyword(@RequestParam String keyword) {
		if (keyword.isEmpty()) {
			return this.getAllStackArchives();
		}
		return stackArchivesService.searchStackByKeyword(keyword);
	}

	/**
	 * insertData API used to read and insert a data into DATABASE. This is only for
	 * CI/CD Purpose. It will be never invoked from Client Side. Due to some concern
	 * I'm developed this API.
	 * 
	 * @return Message
	 */
	@RequestMapping(value = "/insertData", method = RequestMethod.POST)
	public String insertData() {
		stackArchivesService.insertData();
		return "success";
	}

	/**
	 * generateStockHistory API used to generate and return a Particular Day stock
	 * Status for single record.
	 * 
	 * @param uniqueId
	 * @return Data
	 */
	@RequestMapping(value = "/generateStockHistory", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public List<Map<String, Double>> generateStockHistory(@RequestParam UUID uniqueId) {
		List<Map<String, Double>> valuesList = new ArrayList<>();
		Random random = new Random();
		StackArchivesHistoryDto historyDto = stackArchivesService.getStockHistoryById(uniqueId);
		for (double i = 0; i < 24; i++) {
			Map<String, Double> valueMap = new HashMap<>();
			valueMap.put("x", i);
			double randomValue = historyDto.getLow()
					+ (historyDto.getHigh() - historyDto.getLow()) * random.nextDouble();
			valueMap.put("y", randomValue);
			valuesList.add(valueMap);
		}
		return valuesList;
	}

}
