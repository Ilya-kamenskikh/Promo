package com.promo;

public class Model {
	public ArrayList <String> companies = new ArrayList <String>;//Контейнер именкомпаний
	public void addCompany(name){
		String Name;
		this.name = Name;
		companies.add(Name);
		String dir = "C:/companies/"+Name
		File f = new File(dir);
		f.mkdir();
	}
	public void addMovie(name, company, address){
		String company, name, address;
		this.company = company;
		this.name = name;
		this.adress = address
		File f = new File(("C:/companies/" + company + name).txt)
		f.createNewFile();
		BufferedWriter in = new BufferedWriter(f);
		in.write(address, 1,1000);
	}
	public void editCompany(name){
		String name;
		this.name = name
	}
}
