package odev;

import java.util.Random;

public class CUretim extends Uretim {

	@Override
	public int uret() {
		Random rnd = new Random();

		float chance = (float) Math.random(); // 0-1 arasi bir deger al

		if (chance < .5f) {
			return rnd.nextInt(0, 6);
		} else {
			return rnd.nextInt(6, 11);
		}
	}
}
