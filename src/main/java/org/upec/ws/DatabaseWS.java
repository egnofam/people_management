package org.upec.ws;

import java.sql.SQLException;
import java.util.ArrayList;

import org.upec.beans.Modul;
import org.upec.gestionbdd.GestionBDD;

public class DatabaseWS {
	GestionBDD gbdd = new GestionBDD();
	Modul m = new Modul();
	
	public ArrayList<Modul>getModules() throws ClassNotFoundException, SQLException{
		ArrayList<Modul> modules = gbdd.getListModules();
		return modules;
	}
	public ArrayList<Modul> getModulesbyId(int idP) throws ClassNotFoundException, SQLException{
		ArrayList<Modul> modules = gbdd.getListModulesById(idP);
		return modules;
	}
	public void deleteModuleById(int idP) {
		gbdd.deleteModulesById(idP);
	}
	/*public void addModule(int profId,String titre,int nbHeuresCours, int nbHeuresTd,int nbHeuresTp) throws Exception {
		m.se
		m.setTitre(titre);
		m.setNbHeuresCours(nbHeuresCours);
		m.setNbHeureTd(nbHeuresTp);
		m.setNbHeureTp(nbHeuresTp);
		gbdd.ajouterModule(m.getProfId(), m.getTitre(), m.getNbHeuresCours(), m.getNbHeureTd(), m.getNbHeureTp());
	}
	public void updatemodule() {
		gbdd.updateModule(m.getProfId(), m);
	}*/
}
