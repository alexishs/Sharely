package fr.dawan.sharely.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Tuple;

public class DataSet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<String> columnNames = new ArrayList<String>();
	private List<Tuple> records;
	
	public DataSet() {
		super();
	}
	
	public DataSet(String nomsColonnes) {
		super();
		SetColumnNames(nomsColonnes);
	}

	public void SetColumnNames(String nomsColonnes) {
		columnNames.clear();
		if(nomsColonnes != null) {
			columnNames.addAll(Arrays.asList(nomsColonnes.split(";")));
		}
	}
	
	public List<Tuple> getRecords(){
		return records;
	}
	
	public void SetRecords(List<Tuple> records) {
		this.records = records;
	}
	
	public List<List<String>> toStringLists(){
		List<List<String>> StringLists = new ArrayList<List<String>>();
		StringLists.add(columnNames);
		for(Tuple enregistrement : records) {
			List<String> ligne = new ArrayList<String>();
			int nbChamps = enregistrement.getElements().size();
			for (byte iChamp = 0; iChamp < nbChamps; iChamp++) {
				if(enregistrement.get(iChamp) == null) {
					ligne.add("");
				}else {
					ligne.add(enregistrement.get(iChamp).toString());
				}
			}
			StringLists.add(ligne);
		}
		return StringLists;
	}
	
}
