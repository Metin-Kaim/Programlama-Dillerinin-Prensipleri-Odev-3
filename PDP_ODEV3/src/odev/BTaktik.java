package odev;

import java.util.Random;

public class BTaktik extends Taktik {

	@Override
	public int savas() {
		Random rnd = new Random();

		float chance = (float) Math.random(); // 0-1 arasi bir deger al

		if (chance < .5f) {
			return rnd.nextInt(0, 501);
		} else {
			return rnd.nextInt(500, 1001);
		}
	}

}
