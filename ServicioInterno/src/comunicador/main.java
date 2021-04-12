package comunicador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

public class main {
	
	
	public static void main(String []args) {
		
		HashMap<Integer,Cupon> cupones = new HashMap<>();
		
		try {
			int port = 9999;
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) {
				Socket socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				int n = is.read();
				switch (n) {
					//Generar un cupón
					case 0: 
						System.err.println("Caso0 "+n);
						Cupon c;
						int codigo;
						int descuento = is.read();
						System.err.println("descuento "+descuento);
						
						do {codigo = (int)(Math.random()*255)+1;} while(cupones.containsKey(codigo));
						
						c = new Cupon(codigo,descuento);
						cupones.put(codigo,c);
						os.write(codigo);
						break;
					
					//Utilizar un cupón
					case 1:
						System.err.println("Caso1 "+n);
						int cod = is.read();
						System.err.println("cod "+cod);
						
						if(cupones.containsKey(cod)) {
							os.write(cupones.remove(cod).getDescuento());		//Devuelve y borra el cupón
						}else {
							os.write(-1);
						}
						System.err.println("ffl");
						os.flush();
						System.err.println("ush");
						break;
				}
				is.close();
				os.close();
				socket.close();
			}
		} catch (IOException e) {
			System.err.println("Error I/O");
			System.err.println(e);
		}
		
	}
}




