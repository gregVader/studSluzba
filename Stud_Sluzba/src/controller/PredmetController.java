package controller;

import java.util.ArrayList;
import java.util.List;

import gui.MojCentralni;
import klase.BazaPredmeta;
import klase.BazaProfesora;
import klase.BazaStudenta;
import klase.Predmet;
import klase.Profesor;
import klase.Student;

public class PredmetController {
	private static PredmetController instance = null;
	
	private PredmetController() {}
	
	public static PredmetController getInstance()
	{
		if(instance == null)
			instance = new PredmetController();
		return instance;
	}
	
	public boolean dodajPredmet(String sif,String naz,int god,int sem)
	{
		if(BazaPredmeta.getInstance().lookup_predmet(sif)==null)
		{
		    BazaPredmeta.getInstance().dodajPredmet(sif, naz, sem, god);
		    MojCentralni.getInstance().azurirajPrikazPredmet();
		    return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean izmeniPredmet(String sif,String naz,int god,int sem,int id)
	{
		Predmet tmp = BazaPredmeta.getInstance().lookup_predmet(sif);
		if(tmp==null)
		{
			BazaPredmeta.getInstance().IzmeniPredmet(sif, naz, sem, god, id);
			MojCentralni.getInstance().azurirajPrikazPredmet();
			return true;
		}
		else
		{
			if(tmp.getId()==id) {
				BazaPredmeta.getInstance().IzmeniPredmet(sif, naz, sem, god, id);
				MojCentralni.getInstance().azurirajPrikazPredmet();
				return true;
			}
			else {
				return false;
			}
		}
	}

	public Predmet nadjiPredmet(int row) {
		return BazaPredmeta.getInstance().getRow(row);
	}
	
	public void izbrisiPredmet(int row) {
		if(row>=0) {
			Predmet pr = this.nadjiPredmet(row);
			BazaPredmeta.getInstance().izbrisiPredmet(pr.getId());
			MojCentralni.getInstance().azurirajPrikazPredmet();
			
			//ako obrisem predmet, brise se i sa spiska svakog studenta i profesora
			BazaStudenta.getInstance().deleteAllInstancesOfSubject(pr);
			MojCentralni.getInstance().azurirajPrikaz();
			
			BazaProfesora.getInstance().deleteAllInstancesOfSubject(pr);
			MojCentralni.getInstance().azurirajPrikazProfesora();
		}
	}
	
	public void linkStudentPredmet(Student s, Predmet p) {
		p.addStudent(s);
		s.addPredmet(p);
	}
	
	public void unlinkStudentPredmet(Student s, Predmet p) {
		p.removeStudent(s);
		s.removePredmet(p);
	}
	
	public void linkProfesorPredmet(Profesor p, Predmet pr)
	{
		pr.addProfesor(p);
		
	}
	
	public List<Student> getListOfStudents(Predmet p){
		List<Student> stud = new ArrayList<Student>();
		
		for(Student s : p.getmListaStudenata().values()) {
			stud.add(s);
		}
		
		return stud;
	}
}
