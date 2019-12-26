package klase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// klasa za proveru polja za student/profesor/predmet
public class Proveri {
	
	public static boolean isIme(String ime) {
		return ime!=null && ime.matches("[a-zA-Z -]+");
	}
	
	public static boolean isDatum(String datum) {
		try {
            LocalDate.parse(datum, DateTimeFormatter.ofPattern(StringResources.DATEFORMAT));
        } catch (DateTimeParseException e) {
            return false;
        }
		return true;
		//return datum!=null && datum.matches("[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}\\.");
	}
	
	public static boolean isTelefon(String telefon) {
		return telefon!=null && telefon.matches("[+]?[0-9]{7,15}");
	}
	
	public static boolean isBrojIndeksa(String indeks) {
		return indeks!=null && indeks.matches("[a-zA-Z]{2}\\-[0-9]{1,3}\\-[0-9]{4}");
	}
	
	public static boolean isStatus(String status) {
		return status!=null && (status.equals("s") || status.equals("b"));
	}
	
	public static boolean isEmail(String email) {
		return email!=null && email.matches(".+@.+");
	}
	
	public static boolean isGodina(String godina) {
		return godina!=null && (godina.equals("1") || godina.equals("2") || godina.equals("3") || godina.equals("4"));
	}
	
	public static boolean isBrojLK(String lk) {
		return lk!=null && lk.matches("[0-9]+");
	}
	
	public static boolean isSifraPredmeta(String sfr) {
		return sfr!=null && sfr.matches("[a-zA-Z0-9]+");
	}
	
	public static boolean isAdresa(String adr) {
		return adr!=null && !adr.equals("");
	}
	
	public static boolean isImePredmeta(String ime)
	{
		return ime!=null && !ime.equals("");
	}
	
	public static boolean isTitulaOrZvanje(String nesto)
	{
		return nesto!=null && nesto.matches("[a-zA-z]+");
	}
	
	public static boolean isProsek(String pros) {

		if(pros.matches("[0-9]{1,2}(\\.[0-9]{1,2})?")) {
			double d = Double.valueOf(pros);
			if (d<6 || d>10)
				return false;
			else
				return true;
		}
		return false;
	}
	
}
