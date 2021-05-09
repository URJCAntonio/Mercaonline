package practicas.uno.ServicioInterno;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import practicas.uno.PracticaApplication;


@SpringBootApplication
public class main {
	

	public static void main(String []args) {
		SpringApplication.run(PracticaApplication.class, args);
		HashMap<Integer,Cupon> cupones = new HashMap<>();
		 
		try {
			int port = 9999;
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) {
				Socket socket = serverSocket.accept();
				DataInputStream is = new DataInputStream(socket.getInputStream());
				DataOutputStream os = new DataOutputStream(socket.getOutputStream());

				int n = is.readInt();
				switch (n) {
					//Generar un cupón
					case 0: 
						System.err.println("Caso0 "+n);
						Cupon c;
						int codigo;
						int descuento = is.readInt();
						System.err.println("descuento "+descuento);
						
						do {codigo = (int)(Math.random()*200000000)+1;} while(cupones.containsKey(codigo));
						
						c = new Cupon(codigo,descuento);
						cupones.put(codigo,c);
						os.writeInt(codigo);
						break;
					
					//Utilizar un cupón
					case 1:
						System.err.println("Caso1 "+n);
						int cod = is.readInt();
						System.err.println("cod "+cod);
						
						if(cupones.containsKey(cod)) {
							System.out.println(cupones.get(cod));
							os.writeInt(cupones.remove(cod).getDescuento());		//Devuelve y borra el cupón
							
						}else {
							System.out.println("?");
							os.writeInt(-1);
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




