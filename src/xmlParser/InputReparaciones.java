package xmlParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.Reparacion;

public class InputReparaciones {

	public List<Reparacion> inputReparaciones() throws ParserConfigurationException, SAXException, IOException {
		List<Reparacion> response = new ArrayList<Reparacion>();
		try {
			File inputFile = new File("datos.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("reparacion");
//			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
//					System.out.println("Reparacion id: " 
//							+ eElement.getAttribute("id"));
//					System.out.println("idMecanico : " 
//							+ eElement
//							.getElementsByTagName("idMecanico")
//							.item(0)
//							.getTextContent());
//					System.out.println("Descripción : " 
//							+ eElement
//							.getElementsByTagName("descripcion")
//							.item(0)
//							.getTextContent());
//					System.out.println("Coste : " 
//							+ eElement
//							.getElementsByTagName("coste")
//							.item(0)
//							.getTextContent());
//					System.out.println("Precio : " 
//							+ eElement
//							.getElementsByTagName("precio")
//							.item(0)
//							.getTextContent());
//					System.out.println("Fecha de inicio : " 
//							+ eElement
//							.getElementsByTagName("fechaIni")
//							.item(0)
//							.getTextContent());
//					System.out.println("Fecha fin : " 
//							+ eElement
//							.getElementsByTagName("fechaFin")
//							.item(0)
//							.getTextContent());
//					System.out.println("Id Vehiculo : " 
//							+ eElement
//							.getElementsByTagName("idVehiculo")
//							.item(0)
//							.getTextContent());
					int idReparacion = Integer.parseInt(eElement.getAttribute("id"));
					String idVehiculo = eElement
							.getElementsByTagName("idVehiculo")
							.item(0)
							.getTextContent();
					String descripcion = eElement
							.getElementsByTagName("descripcion")
							.item(0)
							.getTextContent();
					Double coste = Double.parseDouble(eElement
							.getElementsByTagName("coste")
							.item(0)
							.getTextContent());
					Double precio = Double.parseDouble(eElement
							.getElementsByTagName("precio")
							.item(0)
							.getTextContent());
					DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			        Date fechaIni = new Date();
					try {
						fechaIni = formatoFecha.parse(eElement
								.getElementsByTagName("fechaIni")
								.item(0)
								.getTextContent());
			        } catch (ParseException e) {
			            e.printStackTrace();
			        } 
					Date fechaFin = new Date();
					try {
						fechaFin = formatoFecha.parse(eElement
								.getElementsByTagName("fechaFin")
								.item(0)
								.getTextContent());
			        } catch (ParseException e) {
			            e.printStackTrace();
			        } 
					int idMecanico = Integer.parseInt(eElement
							.getElementsByTagName("idMecanico")
							.item(0)
							.getTextContent());
					Reparacion reparacion = new Reparacion(idReparacion, idVehiculo, descripcion, coste, precio, fechaIni, fechaFin, idMecanico);
					response.add(reparacion);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("El archivo xml no se ha podido encontrar");
		}
		return response;
	}

}