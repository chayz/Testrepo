
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Zuerst die Dimension der Matrix in der erweiterten Form (ZeilenxSpalten) in der Form AxB z.B. 3x4  eingeben");
		// Wichtig: Matrix in der erweiterten Form eingeben, d.h. mit dem wert rechts des Gleichheitszeichen
		Scanner scann = new Scanner(System.in);
		String dim = scann.nextLine();
		String[] temp=dim.split("x");
		int zeilen=Integer.parseInt(temp[0]);
		int spalten=Integer.parseInt(temp[1]);
		LGS lgs = new LGS(zeilen,spalten);
		
		System.out.println("Geben sie die Gleichungen ein,indem sie durch Leerzeichen getrennt die Koeffizienten eingeben\nEine neue Zeile (Enter drücken) ist eine neue Gleichung eingeben :");
		
		for(int zeilencount=0;zeilencount<zeilen;zeilencount++){//so oft wiederholen, wie es zeilen gibt
			String eingabe = scann.nextLine();

			String[] erg = eingabe.split(" ");
			double[] reihe = new double[erg.length];
			
			for(int i=0;i<erg.length;i++){
				reihe[i]=Double.parseDouble(erg[i]);
			}
			lgs.addiereZeile(reihe);
		}
		if(lgs.pruefeLinearitaet()){//schauen, ob manche gleichungen vielleicht linear abhängig sind
			System.exit(0);
		}
		scann.close();
		
		
		lgs.pivot();// vertauscht manche Zeilen, wenn das Hauptdiagonalelement null ist,damit das lgs einfacher gelöst werden kann
		lgs.loeseLGS();//eigentlicher Algorithmus
	}
	

}
