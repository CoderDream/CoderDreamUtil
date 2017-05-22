package com.coderdream.excavator.bean;

public class Excavator {
	/** 日期 */
	private String workDate;
	
	/** 收入 */
	private Double income;
	
	/** 支出 */
	private Double expend;
	
	/** 类别 */
	private String category;
	
	/** 工地 */
	private String location;
	
	/** 备注 */
	private String comment;
	
	/** 余额 */
	private Double balance;
	
	/** 数量 */
	private Double amount;
	
	/** 单价 */
	private Double unitPrice;
	
	/** 去零 */
	private Double fraction;
	
	/** 毛利率 */
	private Double grossProfitRate;
	
	/** 毛利 */
	private Double grossProfit;

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getExpend() {
		return expend;
	}

	public void setExpend(Double expend) {
		this.expend = expend;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getFraction() {
		return fraction;
	}

	public void setFraction(Double fraction) {
		this.fraction = fraction;
	}

	public Double getGrossProfitRate() {
		return grossProfitRate;
	}

	public void setGrossProfitRate(Double grossProfitRate) {
		this.grossProfitRate = grossProfitRate;
	}

	public Double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	@Override
	public String toString() {
		return "Excavator [workDate=" + workDate + ", income=" + income + ", expend=" + expend + ", category="
				+ category + ", location=" + location + ", comment=" + comment + ", balance=" + balance + ", amount="
				+ amount + ", unitPrice=" + unitPrice + ", fraction=" + fraction + ", grossProfitRate="
				+ grossProfitRate + ", grossProfit=" + grossProfit + "]";
	}
	
}
