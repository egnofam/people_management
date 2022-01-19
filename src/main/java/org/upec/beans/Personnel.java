package org.upec.beans;

public class Personnel {
		//declare variables
		protected int profId;
		protected String nom;
		protected String prenom;
		protected String dateNaissance;
		
		//implement constructors
		public Personnel() {
			
		}
		public Personnel(String nom, String prenom, String dateNaissance) {
			
			this.nom = nom;
			this.prenom = prenom;
			this.dateNaissance = dateNaissance;
			
		}
		public Personnel(int profId, String nom, String prenom, String dateNaissance) {
			this.profId = profId;
			this.nom = nom;
			this.prenom = prenom;
			this.dateNaissance = dateNaissance;
			
		}
		
		//implement getters and setters
		public int getProfId() {
			return profId;
		}
		public void setProfId(int profId) {
			this.profId = profId;
		}
		
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getDateNaissance() {
			return dateNaissance;
		}
		public void setDateNaissance(String dateNaissance) {
			this.dateNaissance = dateNaissance;
		}
		
		//afficher les parametres
		public void afficher() {
			System.out.println("Le personnel "+profId +" " + "se nomme "+nom +" "+prenom+ " et est né en "+dateNaissance);
		
	}

}
