package odev;

import java.util.Random;

public class ATaktik extends Taktik {

	@Override
	public int savas() {
		Random rnd = new Random();

		float chance = (float) Math.random(); // 0-1 arasi bir deger al

		if (chance < .3f) {
			return rnd.nextInt(0, 251);
		} else if (chance < .6f) {
			return rnd.nextInt(251, 501);
		} else {
			return rnd.nextInt(501, 1001);
		}
	}

}
