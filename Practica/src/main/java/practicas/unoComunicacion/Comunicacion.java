package practicas.unoComunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Comunicacion {
	
		public static void enviar() {
			
		String host = "127.0.0.1";
		int puerto = 9999;
		
		try {
			Socket socket = new Socket(host,puerto);
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			
			
			// Envío y recepción de información
			out.write(1);
			
			System.err.println(in.read());
			
			out.close();
			in.close();
			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Host desconocido");
		} catch (IOException e) {
				System.err.println("Error I/O");
		}
		}
}
