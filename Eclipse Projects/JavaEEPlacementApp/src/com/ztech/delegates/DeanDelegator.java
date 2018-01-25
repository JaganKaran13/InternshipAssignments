package com.ztech.delegates;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ztech.dao.OthersDAO;
import com.ztech.dao.OthersDAOImpl;

public class DeanDelegator {
	
	public String join(ArrayList<?> arr)
    {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < arr.size(); i++)
        {
            if (i > 0) output.append(",");
            if (arr.get(i) instanceof String) output.append("\"");
            output.append(arr.get(i));
            if (arr.get(i) instanceof String) output.append("\"");
        }
        return output.toString();
    }
	
	public ArrayList<Integer> getStudentsPlacedCountList(ArrayList<String> departmentList) {
		ArrayList<Integer> studentsPlacedCountList = new ArrayList<Integer>();
		try {
			OthersDAO  othersDAO = new OthersDAOImpl();
			for(int i = 0;i < departmentList.size(); i++) {
				int studentsPlacedCount = othersDAO.noOfStudentsPlaced(departmentList.get(i));
				studentsPlacedCountList.add(studentsPlacedCount);
			}
			return studentsPlacedCountList;
		} catch (SQLException e) {
			
		}
		return null;
	}
	
}
