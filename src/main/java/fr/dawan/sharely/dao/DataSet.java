package fr.dawan.sharely.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Tuple;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataSet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<String> columnNames = new ArrayList<String>();
	@JsonIgnore
	private List<Tuple> enregistrements;
	private List<List<String>> recordList;
	
	public DataSet() {
		super();
	}
	
	public DataSet(String nomsColonnes) {
		super();
		SetColumnNames(nomsColonnes);
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void SetColumnNames(String nomsColonnes) {
		columnNames.clear();
		if(nomsColonnes != null) {
			columnNames.addAll(Arrays.asList(nomsColonnes.split(";")));
		}
	}
	
	public List<Tuple> getEnregistrements(){
		return enregistrements;
	}
	
	public void SetEnregistrements(List<Tuple> enregistrements) {
		this.enregistrements = enregistrements;
		recordList = null;
	}
	
	public List<List<String>> getRecordList() {
		if(recordList == null) {
			remplireRecordList();
		}
		return recordList;
	}
	
	public boolean EstVide() {
		boolean resultat = false;
		if(enregistrements.size() == 1) {
			resultat = true;
			Tuple enregistrement = enregistrements.get(0);
			int nbChamps = enregistrement.getElements().size();
			for (byte iChamp = 0; iChamp < nbChamps; iChamp++) {
				if(enregistrement.get(iChamp) != null) {
					resultat = false;
					break;
				}
			}
		}
		return resultat;
	}

	private void remplireRecordList(){
		if(!EstVide()) {
			recordList = new ArrayList<List<String>>();
			for(Tuple enregistrement : enregistrements) {
				List<String> ligne = new ArrayList<String>();
				int nbChamps = enregistrement.getElements().size();
				for (byte iChamp = 0; iChamp < nbChamps; iChamp++) {
					if(enregistrement.get(iChamp) == null) {
						ligne.add("");
					}else {
						ligne.add(enregistrement.get(iChamp).toString());
					}
				}
				recordList.add(ligne);
			}
		}
	}
	
}
