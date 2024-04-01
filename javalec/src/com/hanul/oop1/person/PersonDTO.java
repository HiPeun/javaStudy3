package com.hanul.oop1.person;

public class PersonDTO {

	
	
	
	//상태 정보 (name, age, height, weight, gender)
	
	private String name;
	private int age;
	private float height;
	private float weight;
	private char fm;

	public void main(String[] args) {}
	
	

	public PersonDTO(String name, int age, float height, float weight, char fm) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.fm = fm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public char getFm() {
		return fm;
	}
	public void setFm(char fm) {
		this.fm = fm;
	}
	@Override
	public String toString() {
		return "PersonDTO [name=" + name + ", age=" + age + ", height=" + height + ", weight=" + weight + ", fm=" + fm
				+ "]";
	}
	
}