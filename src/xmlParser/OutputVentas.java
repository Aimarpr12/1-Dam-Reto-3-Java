package xmlParser;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.Controller;
import modelo.Venta;

public class OutputVentas{
	
	private Controller controller;
	
	public void outputVentas (Controller controller2) {
		
		this.controller=controller2;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// Crear el elemento raíz
			Element rootElement = doc.createElement("ventas");
			doc.appendChild(rootElement);

			// Crear elementos para cada venta
			List<Venta> ventas = controller.getAllVentas(); // Supongamos que tenemos una lista de ventas
			for (Venta venta : ventas) {
				Element ventaElement = doc.createElement("venta");
				ventaElement.setAttribute("id", Integer.toString(venta.getIdVenta()));
				rootElement.appendChild(ventaElement);

				Element vendedorElement = doc.createElement("idVendedor");
				vendedorElement.appendChild(doc.createTextNode(Integer.toString(venta.getIdVendedor())));
				ventaElement.appendChild(vendedorElement);

				Element vehiculoElement = doc.createElement("idVehiculo");
				vehiculoElement.appendChild(doc.createTextNode(venta.getBastidor()));
				ventaElement.appendChild(vehiculoElement);

				Element clienteElement = doc.createElement("idCliente");
				clienteElement.appendChild(doc.createTextNode(venta.getIdCliente()));
				ventaElement.appendChild(clienteElement);

				Element precioElement = doc.createElement("precio");
				precioElement.appendChild(doc.createTextNode(Double.toString(venta.getPrecio())));
				ventaElement.appendChild(precioElement);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String fechaStr = dateFormat.format(venta.getFecha());
				Element fechaElement = doc.createElement("fecha");
				fechaElement.appendChild(doc.createTextNode(fechaStr));
				ventaElement.appendChild(fechaElement);
			}

			// Escribir el archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("ventas.xml"));
			transformer.transform(source, result);
			System.out.println("Archivo ventas.xml creado exitosamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
