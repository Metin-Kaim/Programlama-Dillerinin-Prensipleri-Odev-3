package odev;

import java.util.ArrayList;
import java.util.Random;

public class Koloni {

	public static ArrayList<Integer> semboller = new ArrayList<>();

	Taktik taktik;
	Uretim uretim;
	int populasyon;
	int yemekStogu;
	int kazanma;
	int kaybetme;
	char sembol;

	public Koloni(int populasyon, Taktik taktik, Uretim uretim) {
		this.populasyon = populasyon;
		this.yemekStogu = (int) Math.pow(populasyon, 2);
		this.taktik = taktik;
		this.uretim = uretim;
		this.sembol = sembolOlustur();
	}

	public boolean savasabilirMi() {
		return populasyon > 0 && yemekStogu > 0;
	}

	private char sembolOlustur() {
		int sembol = 0;
		int sayac = 0;
		Random rnd = new Random();
		while (true) {
			sayac++;
			int max = 701, min = 161;

			sembol = rnd.nextInt(min, max);

			if (semboller.size() > 0) {
				if (sayac > max - min - 2) {
					return 254;
				}
				if (semboller.contains(sembol)) { // secilen sembol listede var, baska secilecek
					continue;
				} else { // secilmemis bir sembol bulundu
					semboller.add(sembol);
					return (char) sembol;
				}
			}
			semboller.add(sembol);
			return (char) sembol;
		}
	}
}
