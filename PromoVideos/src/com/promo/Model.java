package com.promo;

public class Model {
	public List<Company> companies = new ArrayList<Company>();//Контейнер именкомпаний
	public void addCompany(name){
		Company company = new Company;
		company.setName();
		company.setBudget();
		companies.add(company);
		String dir = "C:/companies/"+company.getName;
		File f = new File(dir);
		f.mkdir();
	}
	/*public void addMovie(name, company, address){
		String company, name, address;
		this.company = company;
		this.name = name;
		this.adress = address
		File f = new File(("C:/companies/" + company + name).txt)
		f.createNewFile();
		BufferedWriter in = new BufferedWriter(f);
		in.write(address, 1,1000);
	}*/ //Допишу как только, появиться метод addMovie у класса Company.
	

}
