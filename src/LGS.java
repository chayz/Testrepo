


public class LGS {
	
	double[][] matrix;
	int zeilencount=0;//wird in addiereZeile() hochgez�hlt
	int zeilen;
	int spalten;
	
	LGS (int zeilen, int spalten){
		if(zeilen<(spalten-1)){
			System.out.println("Das LGS ist unterbestimmt und enth�lt somit keine eindeutige L�sung!");
			System.exit(0);
		}
		matrix=new double[zeilen][spalten];
		this.zeilen=zeilen;
		this.spalten=spalten;
	}
	
	public void addiereZeile(double[] other){//neue zeile zum lgs hinzuf�gen
		for(int i=0;i<other.length;i++){
			matrix[zeilencount][i]=other[i];
			
		}
		zeilencount++;
	}
	

	
	public void pivot(){//vertauschen manche zeilen, wenn das hauptdiagonalelement Null ist
		int z=0;//zeile
		int s=0;//spalte
		while(z<zeilen && s<spalten){//damit man in der matrix bleibt
			if(matrix[z][s]==0){
				for(int i=z+1;i<zeilen;i++){//alle eintr�ge unter dem element in der hauptdiagonalen pr�fen
					if(matrix[i][s]!=0){
						tauscheZeilen(z, i);
						break;
					}
				}
			}
			
			z++;//zum n�chste hauptdiagonalelement gehen
			s++;
		}
	}
	
	private void tauscheZeilen(int obere,int untere){
		double[] temp = matrix[obere];
		matrix[obere]=matrix[untere];
		matrix[untere]=temp;
		
	}
	
	public void loeseLGS(){
		//obere dreiecksmatrix herstellen
		int p=0;//position in der matrix z.B. p=2 hei�t im Punkt (2,2)
		while(p<spalten){//diagonal durchgehen durch die matrix, p ist die position der in der hauptdiagonalen (0,0), (1,1),...
			for(int i=p+1;i<zeilen;i++){// i iteriert durch alle Zeilen unter dem Hauptdiagonalelement bis zur letzten Zeile
				double faktor = matrix[i][p]/matrix[p][p];
				for(int x=0;x<spalten;x++){//die zeile mit faktor abziehen
					matrix[i][x]-=faktor*matrix[p][x];
				}
			}
			
			p++;
		}
		
		
		//reduced row echelon form herstellen, sodass nur die hauptdiagonale dann noch ungleich null ist
		p=spalten-2;//springe zur unteren rechten Ecke der Matrix
		while(p>=0){
			for(int i=p-1;i>=0;i--){
				double faktor=matrix[i][p]/matrix[p][p];
				for(int x=0;x<spalten;x++){
					matrix[i][x]-=faktor*matrix[p][x];
				}
			}
			p--;//eins diagonal h�her gehen zum n�chsten Element in der Hauptdiagonalen
		}

		System.out.println("Die L�sung des LGS ist:");
		p=0;
		while(p<spalten-1){
			double erg = matrix[p][spalten-1]/matrix[p][p];//Quotient aus dem Eintrag der Hauptdiagonalen und dem Zielfunktionswert der linearen Gleichung
			System.out.println("X"+(p+1)+": "+ erg);
			p++;
		}
	}
	
	public boolean pruefeLinearitaet(){
		// pr�ft alle kombinationen aus 2 zeilen auf linearit�t
		int startuntere=0;//wird hochgez�lt
		for(int obere=0;obere<zeilen;obere++){
			startuntere++;
			for(int untere=startuntere;untere<zeilen;untere++){
				double wert=0;
				boolean warAnderst=false;//flag, ob ein quotient mal anderst als der vorherige war
				for(int i=0;i<spalten;i++){
					if(wert==0){
						wert=matrix[obere][i]/matrix[untere][i];
						continue;
					}
					if(((matrix[obere][i]/matrix[untere][i])!=wert)){//sobald etwas anderst ist als die quotieten davor, ist die zeile nichtmehr linear abh�ngig
						warAnderst=true;
						break;
					}
				}
				wert=0;
				if(!warAnderst){
					System.out.println("Gleichung "+(obere+1)+" und "+(untere+1)+" sind linear abh�ngig.");
					System.out.println("Bitte LGS nochmal mit nur einer dieser Gleichungen eingeben.");
					return true;//war linear abh�ngig
				}
				warAnderst=false;
			}
		}
		return false;//alle gleichungen unabh�ngig
	}

}
