package org.upec.restapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.upec.beans.Modul;
import org.upec.beans.Professeur;
import org.upec.dao.User;
import org.upec.dao.UserDao;
import org.upec.gestionbdd.GestionBDD;
@Path("/api")
public class ApiExposer {
	@Path("/users")
	@GET
	@Produces("text/json")
	public Response getUsers() throws JSONException {

		//get the list of users
		UserDao uDao = new UserDao();
		ArrayList<User> users = (ArrayList<User>) uDao.getAllUsers();
		
		//Convert users to a JSON object
		JSONArray jsonUsers = new JSONArray();
		
		for(int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			JSONObject jsonUser = new JSONObject();
			jsonUser.put("id", u.getId());
			jsonUser.put("name", u.getName());
			jsonUser.put("profession", u.getProfession());
			
			jsonUsers.put(jsonUser);
			
		}
		
		String result = "" + jsonUsers;
		return Response.status(200).entity(result).build();
	}
	
	@Path("/modules")
	@GET
	@Produces("text/json")
	public Response getModules()throws JSONException, ClassNotFoundException, SQLException{
		//get list of modules
		GestionBDD gbdd = new GestionBDD();
		ArrayList<Modul>modules = (ArrayList<Modul>)gbdd.getListModules();
		//Convert modules object to json object
		JSONArray jsonModules = new JSONArray();
		for(int i=0;i<modules.size();i++) {
			Modul m = modules.get(i);
			JSONObject jsonModule = new JSONObject();
			jsonModule.put("id", m.getId());
			jsonModule.put("profId", m.getProfId());
			jsonModule.put("titre", m.getTitre());
			jsonModule.put("nombreHeuresCours", m.getNbHeuresCours());
			jsonModule.put("nombreHeureTd", m.getNbHeureTd());
			jsonModule.put("nombreHeureTp", m.getNbHeureTp());
			
			//add jsonModule object into JsonArray
			jsonModules.put(jsonModule);
		}
		String result = "" + jsonModules;
		return Response.status(200).entity(result).build();
		
	}
	
	@Path("/users")
	@POST	
	@Consumes("application/json")
	@Produces("application/json")	
	public Response addUser(InputStream jUserStream) {
		
		JSONParser jsonParser = new JSONParser();
		org.json.simple.JSONObject jsonObject = null;
		try {
			jsonObject = (org.json.simple.JSONObject)jsonParser.parse(
			      new InputStreamReader(jUserStream, "UTF-8"));
			
			int id = Integer.parseInt((String)jsonObject.get("id"));
			String name = (String)jsonObject.get("name");
			String profession = (String)jsonObject.get("profession");

			User u = new User(id,name,profession);		
			UserDao uDao = new UserDao();
			uDao.addUser(u);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		String result = "{'successs':1}" ;
		//String result =(String)jsonObject.get("id");
		return Response.status(200).entity(result).build();		
	}
	// made by us
	@Path("/modules/{id_prof}")
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	
	public Response updateUModule(@PathParam("id_prof") int id_prof,InputStream jUserStream) throws Exception {
		GestionBDD gbdd = new GestionBDD();
		JSONParser jsonParser = new JSONParser();
		org.json.simple.JSONObject jsonObject ;
		try {
			jsonObject = (org.json.simple.JSONObject) jsonParser.parse(new InputStreamReader(jUserStream,"UTF-8"));
			String titre =((String)jsonObject.get("titre"));
			int nombreHeuresCours =(Integer.parseInt((String)jsonObject.get("nbre_heures_cours")));
			int nombreHeuresTd=(Integer.parseInt((String)jsonObject.get("nbre_heures_td")));
			int nombreHeureTp=(Integer.parseInt((String)jsonObject.get("nbre_heures_tp")));
			int profId= (Integer.parseInt((String)jsonObject.get("idProf")));

			Modul m = new Modul(titre,nombreHeuresCours,nombreHeuresTd,nombreHeureTp,profId);

			gbdd.updateModule(id_prof,m);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result ="{'success':1}";
		return Response.status(200).entity(result).build();
	}
	
	@Path("/modules")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addModule(InputStream jUserStream) throws Exception {
		GestionBDD gbdd = new GestionBDD();
		JSONParser jsonParser = new JSONParser();
		org.json.simple.JSONObject jsonObject;
		jsonObject = (org.json.simple.JSONObject)jsonParser.parse(
		      new InputStreamReader(jUserStream, "UTF-8"));
		
		String titre =((String)jsonObject.get("titre"));
		int nombreHeuresCours =(Integer.parseInt((String)jsonObject.get("nbre_heures_cours")));
		int nombreHeuresTd=(Integer.parseInt((String)jsonObject.get("nbre_heures_td")));
		int nombreHeureTp=(Integer.parseInt((String)jsonObject.get("nbre_heures_tp")));
		int profId= (Integer.parseInt((String)jsonObject.get("idProf")));
		Modul m = new Modul(titre,nombreHeuresCours,nombreHeuresTd,nombreHeureTp,profId);
		System.out.println("Id: "+m.getId());
		gbdd.ajouterModule(m.getProfId(), m.getTitre(), m.getNbHeuresCours(), m.getNbHeureTd(), m.getNbHeureTp());
		String result = "{'success':1}";
		
		return Response.status(200).entity(result).build();
	}
	
	
	@GET
	@Path("/users/{userid}")
	@Produces("application/json")
	public Response getUser(@PathParam("userid") int userid){
		//get the list of users
		UserDao uDao = new UserDao();
		
		User u = uDao.getUser(userid);
		
		JSONObject jsonUser = new JSONObject();
		jsonUser.put("id", u.getId());
		jsonUser.put("name", u.getName());
		jsonUser.put("profession", u.getProfession());
		
		String result = "" + jsonUser;
		return Response.status(200).entity(result).build();
	}
	
	@Path("/modules/{id_prof}")
	@GET
	@Produces("application/json")
	
	public Response getModuleById(@PathParam("id_prof") int id_prof) throws ClassNotFoundException, SQLException {
		GestionBDD gbdd = new GestionBDD();
		ArrayList<Modul> modules = (ArrayList<Modul>)gbdd.getListModulesById(id_prof);
		JSONArray jsonModules  = new JSONArray();
		for(Modul m : modules) {
			JSONObject jsonModule = new JSONObject();
			jsonModule.put("id", m.getId());
			jsonModule.put("idProf", m.getProfId());
			jsonModule.put("titre", m.getTitre());
			jsonModule.put("nbre_heures_cours", m.getNbHeuresCours());
			jsonModule.put("nbre_heures_td", m.getNbHeureTd());
			jsonModule.put("nbre_heures_td", m.getNbHeureTp());
			
			//
			jsonModules.put(jsonModule);
		}
		String result =""+jsonModules;
		return Response.status(200).entity(result).build();
	}
	
	
	@Path("/modules/{id_prof}")
	@DELETE
	@Produces("application/json")
	public Response deleteModuleById(@PathParam("id_prof") int id_prof) throws ClassNotFoundException, SQLException {
		GestionBDD gbdd = new GestionBDD();
		gbdd.deleteModulesById(id_prof);
		
		String result ="{'success':1}";
		return Response.status(200).entity(result).build();
	}
	
	@Path("/professeurs")
	@GET
	@Produces("text/json")
	public Response getProfesseurs()throws JSONException, ClassNotFoundException, SQLException{
		//get list of modules
		GestionBDD gbdd = new GestionBDD();
		ArrayList<Professeur>professeurs = (ArrayList<Professeur>)gbdd.getListProfesseurss();
		//Convert modules object to json object
		JSONArray jsonProfesseurs = new JSONArray();
		System.out.println("EMMANUEL");
		System.out.println(professeurs);
		System.out.println("AZ1");
		for(int i=0;i<professeurs.size();i++) {
			System.out.println("AZ2");
			Professeur p = professeurs.get(i);
			JSONObject jsonProfesseur = new JSONObject();
			jsonProfesseur.put("id", p.getId());
			//jsonModule.put("profId", p.getProfId());
			jsonProfesseur.put("diplome", p.getDiplome());
			jsonProfesseur.put("nom", p.getNom());
			jsonProfesseur.put("prenom", p.getPrenom());
			jsonProfesseur.put("datenaissance", p.getDateNaissance());
			//add jsonModule object into JsonArray
			jsonProfesseurs.put(jsonProfesseur);
		}
		String result = "" + jsonProfesseurs;
		return Response.status(200).entity(result).build();
		
	}
	@Path("/professeurs/{id_prof}")
	@GET
	@Produces("application/json")
	
	public Response getProfesseurById(@PathParam("id_prof") int id_prof) throws ClassNotFoundException, SQLException {
		GestionBDD gbdd = new GestionBDD();
		ArrayList<Professeur> professeurs = (ArrayList<Professeur>)gbdd.getListProfesseursById(id_prof);
		JSONArray jsonProfesseurs  = new JSONArray();
		for(Professeur p : professeurs) {
			JSONObject jsonProfesseur = new JSONObject();
			jsonProfesseur.put("id", p.getId());
			jsonProfesseur.put("idProf", p.getProfId());
			jsonProfesseur.put("nom", p.getNom());
			jsonProfesseur.put("prenom", p.getPrenom());
			jsonProfesseur.put("date_naissance", p.getDateNaissance());
			jsonProfesseur.put("diplome", p.getDiplome());
			
			jsonProfesseurs.put(jsonProfesseur);
		}
		String result =""+jsonProfesseurs;
		return Response.status(200).entity(result).build();
	}
/*
	@Path("/professeurs/{id_prof}")
	@DELETE
	@Produces("application/json")
	public Response deleteProfesseurById(@PathParam("id_prof") int id_prof) throws ClassNotFoundException, SQLException {
		GestionBDD gbdd = new GestionBDD();
		gbdd.deleteProfesseurById(id_prof);
		
		String result ="{'success':1}";
		return Response.status(200).entity(result).build();
	}*/
	
/*
	@Path("/professeurs")
	@POST	
	@Consumes("application/json")
	@Produces("application/json")	
	public Response addProfesseur(InputStream jUserStream) throws Exception {
		
		JSONParser jsonParser = new JSONParser();
		org.json.simple.JSONObject jsonObject = null;
		try {
			jsonObject = (org.json.simple.JSONObject)jsonParser.parse(
			      new InputStreamReader(jUserStream, "UTF-8"));
			
			int id = Integer.parseInt((String)jsonObject.get("id"));
			String nom = (String)jsonObject.get("nom");
			String prenom = (String)jsonObject.get("prenom");
			String date_naissance = (String)jsonObject.get("date_naissance");
			String diplome = (String)jsonObject.get("diplome");
			GestionBDD gbdd = new GestionBDD();
			gbdd.ajouterProfesseur(id, diplome);
			gbdd.ajouterPersonnel(id, nom, prenom, date_naissance);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		String result = "{'successs':1}" ;
		//String result =(String)jsonObject.get("id");
		return Response.status(200).entity(result).build();		
	}*/
	/*
	@Path("/modules/{id_prof}")
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	
	public Response updateProfesseur(@PathParam("id_prof") int id_prof,InputStream jUserStream) throws Exception {
		GestionBDD gbdd = new GestionBDD();
		JSONParser jsonParser = new JSONParser();
		org.json.simple.JSONObject jsonObject ;
		try {
			jsonObject = (org.json.simple.JSONObject) jsonParser.parse(new InputStreamReader(jUserStream,"UTF-8"));
			int id = Integer.parseInt((String)jsonObject.get("id"));
			String nom = (String)jsonObject.get("nom");
			String prenom = (String)jsonObject.get("prenom");
			String date_naissance = (String)jsonObject.get("date_naissance");
			String diplome = (String)jsonObject.get("diplome");

			gbdd.updateProfesseur(id,diplome);
			gbdd.updatePersonnel(id,nom, prenom, date_naissance);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result ="{'success':1}";
		return Response.status(200).entity(result).build();
	}*/

}
