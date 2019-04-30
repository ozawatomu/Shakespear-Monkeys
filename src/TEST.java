import java.util.Random;

public class TEST {
	public static void main(String[] args) {
		Random r = new Random();
		for(int i = 0; i < 30; i++) {
			System.out.println(r.nextDouble());
		}
		System.out.println(r.nextDouble());
	}
}
