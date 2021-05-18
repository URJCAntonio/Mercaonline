<meta charset="UTF-8"/>
<h1># Mercaonline </h1>
<h1>Web de venta online </h1>
<p>Esta web está dirigida a la venta de articulos a múltiples usuarios. Un cliente debe poder ver todos los productos del catalogo. Un cliente podrá filtrar según la categoría del producto que desea. El cliente podrá almacenar los productos en un carro de compra para realizar el pago conjunto. Solo podrán alterar el catálogo los administradores. Un cliente podrá agregar productos a un carro, pero solo se podrá realizar una compra si el usuario está registrado. Un usuario invitado puede registrarse o loguearse manteniendo su carro de la compra.</p>

<p>Entidades:</p>

<ul>
  <li>Administrador: usuario que se encargará de insertar dentro de la web y/o en el catálogo los distintos productos a ofertar.</li>
  <li>Cliente: usuario que comprará en la web. </li>
    <ul>
      <li>Cliente registrado</li>
      <li>Cliente invitado (No definitivo)</li>
  </ul>
 
  <li>Carro de compra: destino de los productos elegidos por el cliente para ser comprados.</li>
  <li>Producto: objeto ofertado en la web y/o en el catálogo.</li>
  <li>Stock: lista que contendrá todos los productos a la venta de la web con su correspondiente cantidad en cada uno de los mismos. </li>
  <li>Pedido: lista de productos comprados por el cliente.</li>
</ul>
<h1>Servicio interno</h1>
<p>
  Queremos ser capaces de generar un cupón tras la compra en la página web, para ofrecer descuentos en la proxima compra. El cupón podría tener alguna clase de código QR para escanear. También se podrá canjear el cupón con un código alfanumérico (por si el dispositivo desde el que se compra no tiene cámara incorporada). Al aplicar dicho descuento, generaremos un pedido nuevo que contendrá los mismos objetos del pedido anterior pero ahora incluyendo el descuento aplicado (si es que alguno de los productos elegidos anteriormente es afectado por el descuento).
 </p>
  
  <h1>Integrantes  (Correo)       Cuenta GitHub</h1>
  <ul> 
  <li> Antonio Martín Córdoba   (a.martinc.2018@alumnos.urjc.es) URJCAntonio</li>
  <li> Ismael Martínez Del Fresno (i.martinezd.2018@alumnos.urjc.es)  Ismael-Martínez-Del-Fresno</li>
  </ul>
  
<h1> Fase 2 </h2>
<br>  
  <h2>Pantallas realizadas y su correspondiente explicación </h2>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818082449400791060/unknown.png">
  <p> Esta pantalla corresponde a la primera pantalla que vemos al ejecutar nuestra aplicacion en local. Desde aqui, podremos registrarnos en la web (aunque todavía no esté al   100% implementado) , iniciar sesión (que no podremos todavía, ya que se implementará en una entrega posterior) y podremos entrar en la web de venta online. </p>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818082495886262302/unknown.png">
  <p> La siguiente pantalla corresponde con el formulario de registro del nuevo cliente. </p>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818082556960047114/unknown.png">
  <p> Esta pantalla corresponde con la tienda de la web. En ella, podremos ver los distintos productos ofertados en la web, ver nuestro carro de compra o incluso, en el caso de los administradores, tener opción de poder insertar nuevos productos en la web. </p>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818082596256612382/unknown.png">
  <p> La pantalla superior corresponde con el tipo de formulario que los administradores de la web podrán rellenar para introducir el producto que deseen. </p>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818083940689510440/unknown.png">
  <p> En esta pantalla podremos visualizar los productos que hayamos añadido al carro de compra. En el caso de esta pantalla, no hemos añadido ninguno de los productos ofertados dentro del carro. Sin embargo, podemos garantizar que podemos añadir productos al carro y a su vez aparecerán en el mismo. </p>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818083999993692190/unknown.png">
  <p> Por último, podemos ver de manera más extensa cualquier producto ofertado en la web. Solo necesitamos hacer click en el producto (podremos hacer click tanto en el nombre como en la propia foto del producto) y podremos ver, entre otras cosas, cúantos productos se pueden comprar y la descripción del producto. </p>
<br>
<br>
<h2> Diagrama de navegación </h2>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818078074369343488/unknown.png">
<br>
<h2> Diagrama Entidad/Relación </h2>
<br>
  <img src="https://cdn.discordapp.com/attachments/522438377165815808/818084046097612820/modelo_entidad_relacion.PNG">
<h2> Diagrama UML </h2>
<img src="https://cdn.discordapp.com/attachments/522438377165815808/818827672623906855/UML.PNG">
<h1>Fase 3</h1>
<h2> Instrucciones para crear jar</h2>
<p> Cuando se descargue el proyecto, hacer clic derecho en el proyecto, ir a Run As y dentro a "Maven Build...". En los goals, metemos "package" y aplicamos los cambios. Con esto, se generará una carpeta llamada target que contendrá el jar de nuestra aplicación. Esto se tiene que hacer tanto con la aplicación del servicio web como la aplicación con el servicio interno.</p>
<h1>Fase 4</h1>
<h2> Vídeo App</h2>
<p>https://www.youtube.com/watch?v=h2-3e5Gj8uk</p>
<h2> Documentación del servicio interno</h2>
<p>El servicio interno es una aplicación java que genera y reconoce cupones. Cuando un cliente realiza una compra, la web lo notifica al servicio interno. Si en la compra se ha intentado utilizar un código de descuento, el servicio interno comprueba que el código corresponda con algún código y se le notifica la respuesta a la web. Además, siempre que se realiza una compra la web avisa al servicio interno, que genera un código de descuento y se lo devuelve a la web. Las aplicaciones se comunican entre ellas mediante mensajes a través de sockets.</p>
<h2> Documentación del servicio interno</h2>
<p>El servicio interno es una aplicación java que genera y reconoce cupones. Cuando un cliente realiza una compra, la web lo notifica al servicio interno. Si en la compra se ha intentado utilizar un código de descuento, el servicio interno comprueba que el código corresponda con algún código y se le notifica la respuesta a la web. Además, siempre que se realiza una compra la web avisa al servicio interno, que genera un código de descuento y se lo devuelve a la web. Las aplicaciones se comunican entre ellas mediante mensajes a través de sockets.</p>
<h2> Diagrama de clases y templates</h2>
<p>El diagrama no ha cambiado desde la fase anterior, ya que solo se añadieron "cabeceras" para las cachés a los métodos ya existentes</p>
<img src="https://cdn.discordapp.com/attachments/522438377165815808/818827672623906855/UML.PNG">
