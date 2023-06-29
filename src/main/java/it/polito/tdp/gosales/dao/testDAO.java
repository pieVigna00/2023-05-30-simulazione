package it.polito.tdp.gosales.dao;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.gosales.model.DailySale;
import it.polito.tdp.gosales.model.Products;
import it.polito.tdp.gosales.model.Retailers;

public class testDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GOsalesDAO dao = new GOsalesDAO();
		List<Retailers> listR = new ArrayList<>();
		List<Products> listP = new ArrayList<>();
		List<DailySale> listS = new ArrayList<>();
		System.out.println(listR.size());
		
		listP = dao.getAllProducts();
		System.out.println(listP.size());
		
		
	}

}
