package com.hanul.oop1.person;

import java.util.List;

public class PersonDAO {

	
	//데이터를 출력하는 메서드 
public void print(List<PersonDTO> personList) {
	
	for(PersonDTO personDTO : personList) {
		System.out.println(personDTO.getName()+ "\t"+ personDTO.getAge() + "\t" +personDTO.getHeight());
		System.out.println(personDTO.getWeight()+ "\t"+ personDTO.getFm() + "\t");
	}
	
	
	
}
	

//이름 검색 
	public List<PersonDTO> searchName(String name, List<PersonDTO> personList){
		
		
		List<PersonDTO> searchResult = new ArrayList<>();
		
		for(PersonDTO personDTO : personList) {
			if(name.equals(personDTO.getName()))
			searchResult.add(personDTO);
					
		}
		
		
		
		return searchResult;
	}
}
	
	
	
	

	
	
	
	

