package xmlParser;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import clases.Reparacion;
import controller.Controller;

public class OutputReparaciones {

	private Controller controller;
	public  void outputReparaciones(Controller controller2) {
		
		this.controller=controller2;
		// Crear lista de reparaciones para escribir en el archivo XML de salida
		List<Reparacion> reparaciones = controller.getAllReparaciones();
	
		// Crear documento XML
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
	
			// Crear elemento raíz
			Element rootElement = doc.createElement("reparaciones");
			doc.appendChild(rootElement);
	
			// Crear elementos para cada reparación
			for (Reparacion reparacion : reparaciones) {
				Element reparacionElement = doc.createElement("reparacion");
				reparacionElement.setAttribute("id", String.valueOf(reparacion.getIdReparacion()));
				rootElement.appendChild(reparacionElement);
	
				Element idMecanicoElement = doc.createElement("idMecanico");
				idMecanicoElement.appendChild(doc.createTextNode(String.valueOf(reparacion.getIdMecanico())));
				reparacionElement.appendChild(idMecanicoElement);
	
				Element descripcionElement = doc.createElement("descripcion");
				descripcionElement.appendChild(doc.createTextNode(reparacion.getDescripcion()));
				reparacionElement.appendChild(descripcionElement);
	
				Element costeElement = doc.createElement("coste");
				costeElement.appendChild(doc.createTextNode(String.valueOf(reparacion.getCoste())));
				reparacionElement.appendChild(costeElement);
	
				Element precioElement = doc.createElement("precio");
				precioElement.appendChild(doc.createTextNode(String.valueOf(reparacion.getPrecio())));
				reparacionElement.appendChild(precioElement);
	
				Element fechaIniElement = doc.createElement("fechaIni");
				fechaIniElement.appendChild(doc.createTextNode(new SimpleDateFormat("dd/MM/yyyy").format(reparacion.getFechaIni())));
				reparacionElement.appendChild(fechaIniElement);
	
				Element fechaFinElement = doc.createElement("fechaFin");
				fechaFinElement.appendChild(doc.createTextNode(new SimpleDateFormat("dd/MM/yyyy").format(reparacion.getFechaFin())));
				reparacionElement.appendChild(fechaFinElement);
			}
	
			// Escribir el contenido del documento XML en un archivo
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("reparaciones.xml"));
				transformer.transform(source, result);
			} catch (TransformerException e) {
				e.printStackTrace();
			}
	
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

}
