package org.upec.beans;



public class Modul {

	//declare properties
	private int id;
	private String titre;
	private int nbHeuresCours;
	private int nbHeureTd;
	private int nbHeureTp;
	private int profId;
	
	//implement constructors
	
	public Modul() {
		//super();
	}
	
	/*public Modul(int id,String titre, int nbHeuresCours, int nbHeureTd, int nbHeureTp, int profId) {
		super();
		this.titre = titre;
		this.nbHeuresCours= nbHeuresCours;
		this.nbHeureTd= nbHeureTd;
		this.nbHeureTp= nbHeureTp;
		this.profId = profId;
		
	}*/
	public Modul(String titre, int nbHeuresCours, int nbHeureTd, int nbHeureTp, int profId) {
		super();
		this.titre = titre;
		this.nbHeuresCours= nbHeuresCours;
		this.nbHeureTd= nbHeureTd;
		this.nbHeureTp= nbHeureTp;
		this.profId = profId;
		
	}
	
   //generate getters and setters
	
	public String getTitre() {
		return titre;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbHeuresCours() {
		return nbHeuresCours;
	}

	public void setNbHeuresCours(int nbHeuresCours) {
		this.nbHeuresCours = nbHeuresCours;
	}

	public int getNbHeureTd() {
		return nbHeureTd;
	}

	public void setNbHeureTd(int nbHeureTd) {
		this.nbHeureTd = nbHeureTd;
	}

	public int getNbHeureTp() {
		return nbHeureTp;
	}

	public void setNbHeureTp(int nbHeureTp) {
		this.nbHeureTp = nbHeureTp;
	}

	public int getProfId() {
		return profId;
	}

	public void setProfId(int profId) {
		this.profId = profId;
	}
				
	//afficher les parametres
	public void afficher() {
		System.out.println("Le module intitulé "+titre +" a "
				+nbHeuresCours+ " heures de cours,  "
				+nbHeureTd+" heures de td et "+nbHeureTp +" heures de tp" );
	}

}
