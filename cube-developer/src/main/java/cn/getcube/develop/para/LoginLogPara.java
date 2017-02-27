package cn.getcube.develop.para;

import java.util.ArrayList;
import java.util.List;

public class LoginLogPara {
	private int cube;
	private String name;
	private String numbers;
	private List<NumberPart> cubes;
	private int limit;
	private int offset;
	public int getCube() {
		return cube;
	}
	public void setCube(int cube) {
		this.cube = cube;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
		try {
			String[] num=numbers.split(",");
			if(num.length>0){
				List<NumberPart> list=new ArrayList<>();
				for(String e:num){
					list.add(new NumberPart(e.split("-")[0], e.split("-")[1]));
				}
				setCubes(list);
			}
		} catch (Exception e) {
			setCubes(null);
		}
		
	}
	public List<NumberPart> getCubes() {
		return cubes;
	}
	public void setCubes(List<NumberPart> cubes) {
		this.cubes = cubes;
	}
	public LoginLogPara(int cube, String name, String numbers, List<NumberPart> cubes) {
		super();
		this.cube = cube;
		this.name = name;
		this.numbers = numbers;
		this.cubes = cubes;
	}
	public LoginLogPara() {
		super();
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}

class NumberPart {
	private String start;
	private String end;
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
	public NumberPart(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}
	public NumberPart() {
		super();
	}
	
}