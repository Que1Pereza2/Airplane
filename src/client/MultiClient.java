package client;

import java.io.IOException;

public class MultiClient {

	public static void main(String[] args) {
		String[] id= {"1","2","3"};
		try {
			for(int i=0;i<3;i++)
				MainClient.main(id[i]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
