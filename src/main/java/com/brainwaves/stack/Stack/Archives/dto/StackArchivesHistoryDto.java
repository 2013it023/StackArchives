package com.brainwaves.stack.Stack.Archives.dto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

/**
 * StackArchivesHistoryDto class is a table, which is automatically created into
 * DB when we are running mvn clean install
 * 
 * @author Saravanan Perumal
 *
 */
@Table(name = "StackArchivesHistory")
@Entity
@EntityScan
@Data
public class StackArchivesHistoryDto {

	/**
	 * The uniqueId, unique id of each and every stock
	 */
	@Id
	@Column(nullable = false, unique = true)
	private UUID uniqueId;

	/**
	 * The date, which is used to identify the particular date
	 */
	private String date;

	/**
	 * The date, name of the company
	 */
	private String symbol;

	/**
	 * The open, opening balance of the day
	 */
	private double open;

	/**
	 * The open, closing balance of the day
	 */
	private double close;

	/**
	 * The low, lowest balance of the day.
	 */
	private double low;

	/**
	 * The high, highest balance of the day
	 */
	private double high;

	/**
	 * The volume, volume of the day
	 */
	private long volume;

	public UUID getUniqueId() {
		return uniqueId;
	}

	public String getDate() {
		return date;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getOpen() {
		return open;
	}

	public double getClose() {
		return close;
	}

	public double getLow() {
		return low;
	}

	public double getHigh() {
		return high;
	}

	public long getVolume() {
		return volume;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

}
