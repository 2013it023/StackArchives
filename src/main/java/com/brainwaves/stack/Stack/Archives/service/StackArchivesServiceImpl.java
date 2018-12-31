package com.brainwaves.stack.Stack.Archives.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.brainwaves.stack.Stack.Archives.dto.StackArchivesHistoryDto;
import com.brainwaves.stack.Stack.Archives.repository.StackArchivesRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * StackArchivesServiceImpl class is used to handle all the business logic's.
 * 
 * @author Saravanan Perumal
 *
 */
@Slf4j
public class StackArchivesServiceImpl implements StackArchivesService {

	private final Logger logger = LoggerFactory.getLogger(StackArchivesServiceImpl.class);

	/**
	 * The stackArchivesRepository, which is used to invoke DATABASE.
	 */
	@Autowired
	private StackArchivesRepository stackArchivesRepository;

	/**
	 * The httpSession, for temporary storage instead of redis server.
	 */
	@Autowired
	private HttpSession httpSession;

	/**
	 * The constant PAGE_NUMBER.
	 */
	private static final String PAGE_NUMBER = "pageNumber";

	/**
	 * The constant PAGE_SIZE.
	 */
	private static final String PAGE_SIZE = "pageSize";

	@Override
	@SuppressWarnings("deprecation")
	public List<StackArchivesHistoryDto> getAllStackArchivesList() {
		int pageSize = Objects.nonNull(httpSession.getAttribute(PAGE_SIZE))
				? Integer.parseInt(String.valueOf(httpSession.getAttribute(PAGE_SIZE)))
				: 20;
		int pageNumber = Objects.nonNull(httpSession.getAttribute(PAGE_NUMBER))
				? Integer.parseInt(String.valueOf(httpSession.getAttribute(PAGE_NUMBER)))
				: 0;
		Page<StackArchivesHistoryDto> archivesHistoryDtoList = stackArchivesRepository
				.findAll(new PageRequest(pageNumber, pageSize));
		httpSession.setAttribute(PAGE_NUMBER, archivesHistoryDtoList.getPageable().getPageNumber());
		httpSession.setAttribute(PAGE_SIZE, archivesHistoryDtoList.getPageable().getPageSize());
		return archivesHistoryDtoList.get().collect(Collectors.toList());
	}

	@Override
	public StackArchivesHistoryDto getStockHistoryById(UUID uniqueId) {
		return stackArchivesRepository.findById(uniqueId).get();
	}

	@Override
	public List<StackArchivesHistoryDto> searchStackByKeyword(String keyword) {
		return stackArchivesRepository.findBySymbol(keyword, new PageRequest(0, 10));
	}

	@Override
	public void insertData() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("G:\\\\prices763fefc.csv"));
			String line = null;
			Scanner scanner = null;
			int index = 0;
			List<StackArchivesHistoryDto> hisotryDtoList = new ArrayList<>();
			while ((line = bufferedReader.readLine()) != null) {
				StackArchivesHistoryDto historyDto = new StackArchivesHistoryDto();
				historyDto.setUniqueId(UUID.randomUUID());
				scanner = new Scanner(line);
				scanner.useDelimiter(",");
				while (scanner.hasNext()) {
					String data = scanner.next();
					if (index == 0)
						historyDto.setDate(String.valueOf(data));
					else if (index == 1)
						historyDto.setSymbol(data);
					else if (index == 2)
						historyDto.setOpen(Double.valueOf(String.valueOf(data)));
					else if (index == 3)
						historyDto.setClose(Double.valueOf(data));
					else if (index == 4)
						historyDto.setLow(Double.valueOf(data));
					else if (index == 5)
						historyDto.setHigh(Double.valueOf(data));
					else if (index == 6)
						historyDto.setVolume(Long.valueOf(data));
					else
						System.out.println("invalid data::" + data);
					index++;
				}
				index = 0;
				hisotryDtoList.add(historyDto);
			}
			Iterable<StackArchivesHistoryDto> historyDto = hisotryDtoList;
			stackArchivesRepository.saveAll(historyDto);
			bufferedReader.close();
		} catch (Exception exception) {
			logger.error("Could Not read file, {}", exception);
		}
	}

}
