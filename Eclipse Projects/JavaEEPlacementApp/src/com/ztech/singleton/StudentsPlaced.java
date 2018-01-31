package com.ztech.singleton;

import java.util.ArrayList;

public class StudentsPlaced {
	
	private static StudentsPlaced studentsPlacedInstance;
	private static ArrayList<Integer> studentsPlacedList;
	
	private StudentsPlaced() {}
	
	public static StudentsPlaced getInstance() {
		if (studentsPlacedInstance == null) {
			studentsPlacedInstance = new StudentsPlaced();
		}
		return studentsPlacedInstance;
	}

	public void insertStudentPlaced(int regno) {
		studentsPlacedList.add(regno);
	}
	
	public static boolean isStudentPlaced(int regno) {
		if(studentsPlacedList == null) {
			return true;
		}
		for(int i = 0; i < studentsPlacedList.size(); i++) {
			if(regno == studentsPlacedList.get(i)) {
				return true;
			}
		}
		return false;
	}
	
}
