package com.ztech.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ztech.beans.DeanBean;
import com.ztech.beans.YearDataBean;
import com.ztech.dao.OthersDAO;
import com.ztech.dao.OthersDAOImpl;

public class DeanDelegator {

	private OthersDAO othersDAO;
	private static Logger logger = Logger.getLogger(DeanDelegator.class.getName());

	public String join(ArrayList<?> arr) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < arr.size(); i++) {
			if (i > 0)
				output.append(",");
			if (arr.get(i) instanceof String)
				output.append("\"");
			output.append(arr.get(i));
			if (arr.get(i) instanceof String)
				output.append("\"");
		}
		return output.toString();
	}

	public ArrayList<Integer> getStudentsPlacedCountList(ArrayList<String> departmentList) {
		ArrayList<Integer> studentsPlacedCountList = new ArrayList<Integer>();
		try {
			othersDAO = new OthersDAOImpl();
			for (int i = 0; i < departmentList.size(); i++) {
				int studentsPlacedCount = othersDAO.noOfStudentsPlaced(departmentList.get(i));
				studentsPlacedCountList.add(studentsPlacedCount);
			}
			return studentsPlacedCountList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error retrieving placed students count list from the database");
		}
		return null;
	}

	public ArrayList<String> getDepartmentList() {
		ArrayList<String> departmentList = null;
		othersDAO = new OthersDAOImpl();
		try {
			departmentList = othersDAO.getDepartmentList();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error retrieving department list from the database");
		}
		return departmentList;
	}

	public ArrayList<Integer> getTotalStudentsList() {
		ArrayList<Integer> studentCountList = null;
		othersDAO = new OthersDAOImpl();
		try {
			studentCountList = othersDAO.getTotalStudentsList();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error retrieving department list from the database");
		}
		return studentCountList;
	}

	public ArrayList<DeanBean> getDeanDisplayDetails() {
		ArrayList<String> departmentList;
		ArrayList<Integer> studentCountList;
		ArrayList<DeanBean> deanBeanList = new ArrayList<DeanBean>();
		othersDAO = new OthersDAOImpl();
		DeanBean deanBean;
		try {
			departmentList = othersDAO.getDepartmentList();
			studentCountList = othersDAO.getTotalStudentsList();
			for (int i = 0; i < departmentList.size(); i++) {
				deanBean = new DeanBean();
				deanBean.setDeptName(departmentList.get(i));
				deanBean.setPlacedPercentage(othersDAO.placementPercentage(departmentList.get(i)));
				deanBean.setStudentCount(studentCountList.get(i));
				deanBean.setStudentPlacedCount(othersDAO.noOfStudentsPlaced(departmentList.get(i)));
				deanBeanList.add(deanBean);
			}
			return deanBeanList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error retrieving department list from the database");
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.warning("Null pointer exception");
		}
		return null;
	}

	public ArrayList<Integer> getStudentCountByYear() {
		ArrayList<Integer> byYearStudentCountList = new ArrayList<Integer>();
		ArrayList<YearDataBean> yearDataList = othersDAO.getByYearDetails();
		for (int i = 0; i < yearDataList.size(); i++) {
			System.out.println(yearDataList.get(i).getStudentCount());
			byYearStudentCountList.add(yearDataList.get(i).getStudentCount());
		}
		return byYearStudentCountList;
	}
	
	public ArrayList<Integer> getStudentPlacedCountByYear() {
		ArrayList<Integer> byYearStudentPlacedCountList = new ArrayList<Integer>();
		ArrayList<YearDataBean> yearDataList = othersDAO.getByYearDetails();
		for (int i = 0; i < yearDataList.size(); i++) {
			System.out.println(yearDataList.get(i).getStudentPlaced());
			byYearStudentPlacedCountList.add(yearDataList.get(i).getStudentPlaced());
		}
		return byYearStudentPlacedCountList;
	}
}
