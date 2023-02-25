package com.mca.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int age;
	private String species;
	private String images;
	private boolean adopted;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isAdopted() {
		return adopted;
	}
	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}
	public Pet(Long id, String name, int age, String species, String images, boolean adopted) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.species = species;
		this.images = images;
		this.adopted = adopted;
	}

	
}
