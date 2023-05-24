package odev;

import java.util.Random;

public class AUretim extends Uretim {

	@Override
	public int uret() {
		Random rnd = new Random();

		float chance = (float) Math.random(); // 0-1 arasi bir deger al

		if (chance < .8f) {
			return rnd.nextInt(0, 6);
		} else {
			return rnd.nextInt(6, 11);
		}
	}

}
