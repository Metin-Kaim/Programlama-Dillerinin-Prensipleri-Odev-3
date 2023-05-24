package odev;

import java.util.Random;

public class CTaktik extends Taktik {

	@Override
	public int savas() {
		Random rnd = new Random();

		float chance = (float) Math.random(); // 0-1 arasi bir deger al

		if (chance < .1f) {
			return rnd.nextInt(0, 126);
		} else if (chance < .2f) {
			return rnd.nextInt(126, 251);
		} else if (chance < .3f) {
			return rnd.nextInt(251, 501);
		} else {
			return rnd.nextInt(501, 1001);
		}
	}

}
