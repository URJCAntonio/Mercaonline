package practicas.uno;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import practicas.uno.ServicioInterno.Cupon;

@SpringBootApplication
public class PracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
		
	}
}

