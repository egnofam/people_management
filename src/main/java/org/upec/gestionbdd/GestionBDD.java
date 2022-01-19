package org.upec.gestionbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.upec.beans.Modul;
import org.upec.beans.Professeur;

public class GestionBDD {

	String url="jdbc:mysql://localhost:3306/gestionpersonnelbd";
	String uname="root";
	String pass="Chiffres2@19";
	
	Connection con;
	Statement state;
	static final String TABLE_PROFESSEUR = "professeur1";
	static final String TABLE_PERSONNEL = "personnel1";
	static final String TABLE_MODULE = "module1";
	
	public GestionBDD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,uname,pass);		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		public void ajouterProfesseur(int mat,String diplome) {
		try {
			String query = String.format("INSERT INTO %s  VALUES(?,?);", TABLE_PROFESSEUR);
			PreparedStatement preparestatement = con.prepareStatement(query);
			preparestatement.setInt(1, mat);
			preparestatement.setString(2, diplome);
			
			System.out.print(preparestatement);
			preparestatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void ajouterPersonnel(int profId,String nom,String prenom, String dateNaissance)throws Exception {
		
		try {
			String query = String.format("INSERT INTO %s (prof_id,nom,prenom,date_naissance)  VALUES(?,?,?,?)", TABLE_PERSONNEL);
			PreparedStatement preparestatement = con.prepareStatement(query);
			//preparestatement.setString(1, TABLE_PERSONNEL);
			preparestatement.setInt(1, profId);
			preparestatement.setString(2, nom);
			preparestatement.setString(3, prenom);
			preparestatement.setString(4, dateNaissance);
			System.out.println(preparestatement);
			preparestatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ajouterModule(int profId,String titre,int nbHeuresCours, int nbHeuresTd,int nbHeuresTp)throws Exception {
		
		try {
			String query = String.format("INSERT INTO %s (prof_id,titre,nb_heures_cours,nb_heures_td,nb_heures_tp)  VALUES(?,?,?,?,?)", TABLE_MODULE);
			PreparedStatement preparestatement = con.prepareStatement(query);
			//preparestatement.setString(1, TABLE_MODULE);
			preparestatement.setInt(1, profId);
			preparestatement.setString(2, titre);
			preparestatement.setInt(3, nbHeuresCours);
			preparestatement.setInt(4, nbHeuresTd);
			preparestatement.setInt(5, nbHeuresTp);	
			System.out.println("preparestatement"+preparestatement);

			preparestatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateModule(int id_prof,Modul module) throws SQLException {
		System.out.println("beginning of the update");
		String request = String.format("UPDATE %s SET titre = ?,nb_heures_cours=? , nb_heures_td=?, nb_heures_tp=? WHERE prof_id=?",TABLE_MODULE);
		PreparedStatement preparedStatement = con.prepareStatement(request);
		//preparedStatement.setInt(1, module.getProfId());
		preparedStatement.setString(1, module.getTitre());
		preparedStatement.setInt(2, module.getNbHeuresCours());
		preparedStatement.setInt(3, module.getNbHeureTd());
		preparedStatement.setInt(4, module.getNbHeureTp());
		preparedStatement.setInt(5, id_prof);
		
		preparedStatement.executeUpdate();
	}
	
	//get list professeurs
	public ArrayList<Professeur> getListProfesseurs() throws SQLException{
		ArrayList<Professeur> listProfesseurs = new ArrayList<Professeur>();
		String request = "select id,nom, prenom,date_naissance from professeur1 as prof1,personnel1 as pers1 where prof1.id==pers1.prof_id";
		
			PreparedStatement preparestatement = con.prepareStatement(request);
			ResultSet rs = state.executeQuery(request);
			while(rs.next()) {
				int id = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				String dateNaissance = rs.getString(4);
				//
				Professeur professeur = new Professeur();
				professeur.setId(id);
				professeur.setNom(nom);
				professeur.setPrenom(prenom);
				professeur.setDateNaissance(dateNaissance);
				//
				listProfesseurs.add(professeur);
				
				}
			return listProfesseurs;
	}

	
	//recuperer la liste des modules par professeur
	public ArrayList<Modul> getListModulesByProfesseur(int profId) throws SQLException, ClassNotFoundException {
		
		ArrayList<Modul> modules = new ArrayList<>();
		String query = "SELECT * FROM ? WHERE prof_id = ?";
		PreparedStatement preparestatement = con.prepareStatement(query);
		preparestatement.setString(1,TABLE_MODULE);
		preparestatement.setInt(2, profId);
		ResultSet rs = state.executeQuery(query);
		while(rs.next()) {
			String titre = rs.getString(3);
			int nbhc = rs.getInt(4);
			int nbhtd =rs.getInt(5);
			int nbhtp = rs.getInt(6);
			Modul module = new Modul();
			module.setTitre(titre);
			module.setNbHeuresCours(nbhc);
			module.setNbHeureTd(nbhtd);
			module.setNbHeureTp(nbhtp);
			modules.add(module);
		}
		return modules;
	}
	
	//recuperer la liste des modules  
		public ArrayList<Modul> getListModules() throws SQLException, ClassNotFoundException {
			System.out.println("test inside bdd");
			ArrayList<Modul> modules = new ArrayList<>();
			Statement statement = null;
	        ResultSet rs = null;
	        System.out.println("first rs = "+rs);
	        try {
	            statement = con.createStatement();
	            System.out.println("first con = "+con);
	            // Exécution de la requête
	            rs = statement.executeQuery("SELECT * FROM module1");
	            System.out.println("second rs"+rs);
	            while(rs.next()) {
	            	 System.out.println("third rs"+rs);
	            	 int id = rs.getInt(1);
					int prof_id = rs.getInt(2);
					String titre = rs.getString(3);
					int nbhc = rs.getInt(4);
					int nbhtd =rs.getInt(5);
					int nbhtp = rs.getInt(6);
					Modul module = new Modul();
					module.setId(id);
					module.setProfId(prof_id);
					module.setTitre(titre);
					module.setNbHeuresCours(nbhc);
					module.setNbHeureTd(nbhtd);
					module.setNbHeureTp(nbhtp);
					modules.add(module);
					System.out.println("----------------------");
					for(Modul m : modules){

						System.out.println("m "+m.getTitre() + "tp : "+ m.getNbHeureTp());
					}
    
	            }
	        } catch (SQLException e) {
	        } finally {
	            // Fermeture de la connexion
	            try {
	                if (rs != null)
	                    rs.close();
	                if (statement != null)
	                    statement.close();
	                if (con != null)
	                    con.close();
	            } catch (SQLException ignore) {
	            }
	        }
	        
	       return modules;
		
		}
	

//recuperer la liste des modules par Id
		public ArrayList<Modul> getListModulesById(int idP) throws SQLException, ClassNotFoundException {
			ArrayList<Modul> modules = new ArrayList<>();
			Statement statement = null;
	        ResultSet rs = null;
	        System.out.println("first rs = "+rs);
	        try {
	            statement = con.createStatement();
	            System.out.println(statement);
	            // Exécution de la requête
	            rs = statement.executeQuery("SELECT * FROM module1 where module1.prof_id="+idP+"");
	            System.out.println("second rs"+rs);
	            while(rs.next()) {
	            	 System.out.println("third rs"+rs);
	            	int id = rs.getInt(1);
					int prof_id = rs.getInt(2);
					String titre = rs.getString(3);
					int nbhc = rs.getInt(4);
					int nbhtd =rs.getInt(5);
					int nbhtp = rs.getInt(6);
					Modul module = new Modul();
					module.setId(id);
					module.setProfId(prof_id);
					module.setTitre(titre);
					module.setNbHeuresCours(nbhc);
					module.setNbHeureTd(nbhtd);
					module.setNbHeureTp(nbhtp);
					modules.add(module);
					System.out.println("----------------------");
					for(Modul m : modules){

						System.out.println("m "+m.getTitre());
					}
  
	            }
	        } catch (SQLException e) {
	        } finally {
	            // Fermeture de la connexion
	            try {
	                if (rs != null)
	                    rs.close();
	                if (statement != null)
	                    statement.close();
	                if (con != null)
	                    con.close();
	            } catch (SQLException ignore) {
	            }
	        }
	        
	       return modules;
	
		}
		//recuperer la liste des modules  
				public ArrayList<Professeur> getListProfesseurss() throws SQLException, ClassNotFoundException {
					System.out.println("test inside bdd");
					ArrayList<Professeur> professeurs = new ArrayList<>();
					Statement statement = null;
			        ResultSet rs = null;
			        System.out.println("first rs = "+rs);
			        try {
			            statement = con.createStatement();
			            System.out.println("first con = "+con);
			            // Exécution de la requête
			            rs = statement.executeQuery("SELECT * FROM personnel1 INNER JOIN professeur1  ON professeur1.id=personnel1.prof_id");
			            System.out.println("second rs"+rs);
			            while(rs.next()) {
			            	System.out.println("third rs"+rs);
			            	int id = rs.getInt(1);
			            	int prof_id = rs.getInt(2);
							String nom = rs.getString(3);

							String prenom = rs.getString(4);
							String date_naissance =rs.getString(5);
							String diplome = rs.getString(6);
							System.out.println("id: "+id + "--prof id : "+prof_id+ " -- nom:  "+nom + "--prenom:" +prenom +"--date_naissance:"+date_naissance +"--diplome: " +diplome);
							Professeur professeur = new Professeur();
							professeur.setId(id);
							professeur.setProfId(prof_id);
							professeur.setNom(nom);
							professeur.setPrenom(prenom);
							professeur.setDateNaissance(date_naissance);
							professeur.setDiplome(diplome);
							professeurs.add(professeur);
							System.out.println("----------------------");
							/*for(Professeur p : professeurs){
								System.out.println("m "+p.getPrenom());
							}*/
		    
			            }
			        } catch (SQLException e) {
			        } finally {
			            // Fermeture de la connexion
			            try {
			                if (rs != null)
			                    rs.close();
			                if (statement != null)
			                    statement.close();
			                if (con != null)
			                    con.close();
			            } catch (SQLException ignore) {
			            }
			        }
			        
			       return professeurs;

				}
				
				//delete module par id
				public void deleteModulesById(int idP)   {
					//ArrayList<Modul> modules = new ArrayList<>();
					Statement statement = null;
			        ResultSet rs = null;
			          try {
							statement = con.createStatement();
						    System.out.println(statement);
				            // Exécution de la requête
				            statement.executeUpdate("DELETE FROM module1 WHERE module1.prof_id="+idP+"");
				            //System.out.println("first rs ="+rs);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        
			            System.out.println("successfully deleted");        
			
				 }
				
				

				//recuperer la liste des modules par Id
						public ArrayList<Professeur> getListProfesseursById(int idP) throws SQLException, ClassNotFoundException {
							ArrayList<Professeur> professeurs = new ArrayList<>();
							Statement statement = null;
					        ResultSet rs = null;
					        System.out.println("first rs = "+rs);
					        try {
					            statement = con.createStatement();
					            System.out.println(statement);
					            // Exécution de la requête
					            rs = statement.executeQuery("SELECT * FROM personnel1 INNER JOIN professeur1 ON personnel1.prof_id = professeur1.id where professeur1.id="+idP+"");
					            System.out.println("second rs"+rs);
					            while(rs.next()) {
					            	 System.out.println("third rs"+rs);
					            	int id = rs.getInt(1);
									int prof_id = rs.getInt(2);
									String nom = rs.getString(3);
									String prenom = rs.getString(4);
									String date_naissance =rs.getString(5);
									String diplome = rs.getString(6);
									Professeur professeur = new Professeur();
									professeur.setId(id);
									professeur.setProfId(prof_id);
									professeur.setNom(nom);
									professeur.setPrenom(prenom);
									professeur.setDateNaissance(date_naissance);
									professeur.setDiplome(diplome);
									professeurs.add(professeur);
									System.out.println("----------------------");
									
									for(Professeur p : professeurs){

										System.out.println("p "+p.getId());
									}
				  					
					            }
					        } catch (SQLException e) {
					        } finally {
					            // Fermeture de la connexion
					            try {
					                if (rs != null)
					                    rs.close();
					                if (statement != null)
					                    statement.close();
					                if (con != null)
					                    con.close();
					            } catch (SQLException ignore) {
					            }
					        }
					        
					       return professeurs;
					
						}
		
						//delete module par id
						public void deleteProfesseurById(int idP)   {
							//ArrayList<Modul> modules = new ArrayList<>();
							Statement statement = null;
					        ResultSet rs = null;
					          try {
									statement = con.createStatement();
								    System.out.println(statement);
						            // Exécution de la requête
								    String request = "DELETE p FROM personnel1 p INNER JOIN professeur1 prof ON personnel1.prof_id = professeur1.id WHERE professeur1.id = "+idP+"";
								   // String request1 = "DELETE FROM personnel1 where professeur1.id in (select * from )"
								    statement.executeUpdate(request);
						            //statement.executeUpdate("DELETE FROM module1 WHERE module1.prof_id="+idP+"");
						            System.out.println("first rs ="+rs);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
					        
					            System.out.println("second rs = "+rs);        
					
						 }
						
					public void updateProfesseur(int id, String diplome) throws SQLException {
						String request = String.format("UPDATE %s SET id = ?,diplome=?  WHERE id=?",TABLE_PROFESSEUR);
						PreparedStatement preparedStatement = con.prepareStatement(request);
						preparedStatement.setInt(1, id);
						preparedStatement.setString(2, diplome);
						preparedStatement.executeUpdate();
					}
					public void updatePersonnel(int id, String nom, String prenom, String date_naissance) throws SQLException {
						String request = String.format("UPDATE %s SET id = ?,nom=?,prenom =? , date_naissance=? WHERE id=?",TABLE_PERSONNEL);
						PreparedStatement preparedStatement = con.prepareStatement(request);
						preparedStatement.setInt(1, id);
						preparedStatement.setString(2, nom);
						preparedStatement.setString(3, prenom);
						preparedStatement.setString(4, date_naissance);
						preparedStatement.executeUpdate();
					}
					
				
	
}