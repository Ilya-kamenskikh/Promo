package com.promo.clients;

import java.util.ArrayList;
import java.util.List;

public class Companies {
	
	public class Company{
		private String  name;
		private Integer budget;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getBudget() {
			return budget;
		}
		public void setBudget(Integer budget) {
			this.budget = budget;
		}
	}
	private List<Company> companies = new ArrayList<Company>();
	public void addCompany(Company C){
		companies.add(C);
	}
	public List<Company> getCompanies() {
		return companies;
	}
	
}
