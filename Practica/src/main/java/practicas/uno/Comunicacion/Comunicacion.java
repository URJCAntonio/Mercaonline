package practicas.uno.Comunicacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Comunicacion {
	
	public static int enviar(String fun, int num) {
		System.err.println("enviar");
		String host = "127.0.0.1";
		int puerto = 9999;
		
		try {
			Socket socket = new Socket(host,puerto);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			
			// Envío y recepción de información
			
			//Especificar usar o generar cupón
			if(fun=="usar") {
				out.writeInt(1);
			}else {
				out.writeInt(0);
			}
			out.flush();
			
			out.writeInt(num);
			out.flush();
			
			int cod = in.readInt();
			System.err.println(fun+" espacio "+cod);
			out.close();
			in.close();
			socket.close();
			System.err.println("Bien");
			return cod;
			
		} catch (UnknownHostException e) {
			System.err.println("Host desconocido: "+e);
		} catch (IOException e) {
			System.err.println("Error I/O: "+e);
		}
		System.err.println("Mierda");
		return -1;
	}
}
