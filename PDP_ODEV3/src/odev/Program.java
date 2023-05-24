package odev;

import java.util.ArrayList;

public class Program {

	public static void main(String[] args) {

		Oyun oyun = new Oyun();
		
		Taktik aTaktik = new ATaktik();
		Taktik bTaktik = new BTaktik();
		Taktik cTaktik = new CTaktik();

		Uretim aUretim = new AUretim();
		Uretim bUretim = new BUretim();
		Uretim cUretim = new CUretim();

		Taktik[] taktikler = { aTaktik, bTaktik, cTaktik };
		Uretim[] uretimler = { aUretim, bUretim, cUretim };

		ArrayList<Integer> populations = oyun.populasyonlariAl();
		
		ArrayList<Koloni> koloniler = oyun.koloniOlustur(populations, taktikler, uretimler);
		
		oyun.savasBaslat(koloniler);

	}
}
