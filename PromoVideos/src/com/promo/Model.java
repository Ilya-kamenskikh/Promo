package com.promo;

public class Model {
	public ArrayList <String> companies = new ArrayList <String>;//��������� ������������
	public void addCompany(name){
		Company company = new Company;
		company.setName();
		company.setBudget();
		companies.add(company.getName());
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
	}*/ //������ ��� ������, ��������� ����� addMovie � ������ Company.
	

}
