package odev;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Oyun {

	public ArrayList<Integer> populasyonlariAl() {
		System.out.println("Kolonilerin Populasyon Miktarlarini Girin:");
		try (Scanner input = new Scanner(System.in)) {
			String inputLine = input.nextLine();

			String[] values = inputLine.split(" ");
			ArrayList<Integer> populations = new ArrayList<>();

			for (int i = 0; i < values.length; i++) {
				populations.add(Integer.parseInt(values[i]));
			}
			return populations;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Koloni> koloniOlustur(ArrayList<Integer> populations, Taktik[] taktikler, Uretim[] uretimler) {
		ArrayList<Koloni> koloniler = new ArrayList<>();
		Random rnd = new Random();

		for (int i = 0; i < populations.size(); i++) {
			koloniler.add(new Koloni(populations.get(i), taktikler[rnd.nextInt(0, taktikler.length)],
					uretimler[rnd.nextInt(0, uretimler.length)]));
		}
		return koloniler;
	}

	public void savasBaslat(ArrayList<Koloni> koloniler) {

		int turSayisi = 0;
		
		savasRaporu(koloniler, turSayisi);
		
		while (true) {
			turSayisi++;
			if (savasBittiMi(koloniler))
				break;

			turBaslat(koloniler);

			turBitir(koloniler);
			savasRaporu(koloniler, turSayisi);
		}
	}

	private void savasRaporu(ArrayList<Koloni> koloniler, int turSayisi) {
		System.out.println("---------------------------------------------------------------");
		System.out.println("Tur Sayisi : " + turSayisi);
		System.out.println("Koloni  Populasyon      Yemek Stogu     Kazanma  Kaybetme");
		for (int i = 0; i < koloniler.size(); i++) {
			if (koloniler.get(i).savasabilirMi())
				System.out.printf("%2c%14d%15d%13d%9d\n", koloniler.get(i).sembol, koloniler.get(i).populasyon,
						koloniler.get(i).yemekStogu, koloniler.get(i).kazanma, koloniler.get(i).kaybetme);
			else
				System.out.printf("%2c%13c%c%14c%c%12c%c%8c%c\n", koloniler.get(i).sembol, '-', '-', '-', '-', '-', '-',
						'-', '-');
		}
	}

	private boolean savasBittiMi(ArrayList<Koloni> koloniler) {

		int saglamKoloniSayisi = 0;

		for (int i = 0; i < koloniler.size(); i++) {
			if (koloniler.get(i).savasabilirMi()) {
				saglamKoloniSayisi++;
				if (saglamKoloniSayisi > 1) { // savas bitmedi
					return false;
				}
			}
		}
		return true; // savas bitti
	}

	private boolean koloniKontrol(Koloni koloni1, Koloni koloni2) {
		return koloni1.savasabilirMi() && koloni2.savasabilirMi();
	}

	private boolean koloniKontrol(Koloni koloni) {
		return koloni.savasabilirMi();
	}

	private void turBitir(ArrayList<Koloni> koloniler) {
		for (int i = 0; i < koloniler.size(); i++) {
			if (!koloniKontrol(koloniler.get(i)))
				continue; // koloni savasamaz durumda ise islem gorme

			koloniler.get(i).yemekStogu += koloniler.get(i).uretim.uret();
			koloniler.get(i).populasyon += koloniler.get(i).populasyon / 5; // %20
			koloniler.get(i).yemekStogu -= koloniler.get(i).populasyon * 2;
		}
	}

	private void turBaslat(ArrayList<Koloni> koloniler) {
		for (int i = 0; i < koloniler.size() - 1; i++) {
			for (int j = i + 1; j < koloniler.size(); j++) {

				if (!koloniKontrol(koloniler.get(i), koloniler.get(j)))
					continue; // kolonilerden harhangi biri savasamaz durumda ise islem gorme

				int ilkDeger = koloniler.get(i).taktik.savas();
				int ikinciDeger = koloniler.get(j).taktik.savas();

				int fark = Math.abs(ilkDeger - ikinciDeger);
				fark /= 10; // yüzde oranı

				if (ilkDeger > ikinciDeger) { // ilk koloni kazanır (i = win / j = lose)
					savasDurumu(koloniler, i, j, fark);
				} else if (ilkDeger < ikinciDeger) { // ikinci koloni kazanır (j = win / i = lose)
					savasDurumu(koloniler, j, i, fark);
				} else { // savas degerleri esit
					if (koloniler.get(i).populasyon > koloniler.get(j).populasyon) { // ilk populasyon fazla i::win
						savasDurumu(koloniler, i, j, fark);
					} else if (koloniler.get(i).populasyon < koloniler.get(j).populasyon) { // ikinci populasyon fazla
																							// j::win
						savasDurumu(koloniler, j, i, fark);
					} else { // populasyonlar esit
						int berabere = new Random().nextInt(0, 2);

						if (berabere == 0) // ilk koloni kazandi
							savasDurumu(koloniler, i, j, fark);
						else // ikinci koloni kazandı
							savasDurumu(koloniler, j, i, fark);
					}

				}
			}
		}
	}

	private void savasDurumu(ArrayList<Koloni> koloniler, int win, int lose, int fark) {
		koloniler.get(lose).populasyon -= Math.ceil(koloniler.get(lose).populasyon * fark / (float) 100);
		koloniler.get(win).yemekStogu += Math.ceil(koloniler.get(lose).yemekStogu * fark / (float) 100);
		koloniler.get(lose).yemekStogu -= Math.ceil(koloniler.get(lose).yemekStogu * fark / (float) 100);

		koloniler.get(win).kazanma++;
		koloniler.get(lose).kaybetme++;
	}
}
