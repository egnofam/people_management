package org.upec.beans;
import java.util.ArrayList;

public class Professeur extends Personnel {
	
	//attributes
	private int id;
	private String diplome;
	ArrayList<Modul> modules = new ArrayList<Modul>();
	
	public Professeur() {
		super();
	}
	public Professeur(int id,String nom, String prenom,String dateNaissance,String diplome) {
		super(nom, prenom,dateNaissance);
		this.id=id;
		this.diplome = diplome;
	}
	
	public Professeur(int id,int prof_id,String nom, String prenom,String dateNaissance,String diplome) {
		super(prof_id,nom,prenom,dateNaissance);
		this.id=id;
		this.diplome = diplome;
	}

	//implement getters and setters
	
	
	public String getDiplome() {
		return diplome;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	
	
	public ArrayList<Modul> getModules() {
		return modules;
	}

	public void setModules(ArrayList<Modul> modules) {
		this.modules = modules;
	}

	//implement methods
	public ArrayList<Modul> ajouterModule(Modul module) {
		//module = new Module();
		this.modules.add(module);
		return modules;
	}
	/*public ArrayList<Module> supprimer_module(Module module) {
		//module = new Module();
		this.modules.remove(module);
		return modules;
	}*/
	
	//afficher les parametres
	/*public void afficher() {
		for (int i = 0; i<modules.size();i++) {
			System.out.println("Il est responsable de ces  modules  "+modules.get(i).titre);
		}
	}*/

}
