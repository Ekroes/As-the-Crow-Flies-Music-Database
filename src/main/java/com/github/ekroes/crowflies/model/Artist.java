package com.github.ekroes.crowflies.model;
// Entity model; representing an artist

// @author Elizabeth

public class Artist {

	private Integer id = null;
	private String name = "";
	private String start = "";
	private String end = "";

	public Artist(String name) {
		this.name = name;
	}
	
	public Artist (String name, String start, String end){
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public Artist(Integer id, String name, String start, String end) {

		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
	}

	public Integer getId() {
		return id;
	}
	

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	
}
