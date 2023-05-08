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

import modelo.Venta;

public class InputVentas {

	public List<Venta> inputVentas() throws ParserConfigurationException, SAXException, IOException {
		List <Venta> response = new ArrayList<Venta>();
		try {
			File inputFile = new File("datos.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("venta");
//			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
//				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
//					System.out.println("Venta id: " 
//							+ eElement.getAttribute("id"));
//					System.out.println("idVendedor : " 
//							+ eElement
//							.getElementsByTagName("idVendedor")
//							.item(0)
//							.getTextContent());
//					System.out.println("Bastidor : " 
//							+ eElement
//							.getElementsByTagName("idVehiculo")
//							.item(0)
//							.getTextContent());
//					System.out.println("DNI Cliente : " 
//							+ eElement
//							.getElementsByTagName("idCliente")
//							.item(0)
//							.getTextContent());
//					System.out.println("Precio : " 
//							+ eElement
//							.getElementsByTagName("precio")
//							.item(0)
//							.getTextContent());
//					System.out.println("Fecha de venta: " 
//							+ eElement
//							.getElementsByTagName("fecha")
//							.item(0)
//							.getTextContent());
					int idVenta = Integer.parseInt(eElement.getAttribute("id"));
					int precio = Integer.parseInt( eElement
							.getElementsByTagName("precio")
							.item(0)
							.getTextContent());
			        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			        Date fecha = new Date();
					try {
			            fecha = formatoFecha.parse(eElement
								.getElementsByTagName("fecha")
								.item(0)
								.getTextContent());
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
					int idVendedor = Integer.parseInt(eElement
							.getElementsByTagName("idVendedor")
							.item(0)
							.getTextContent());
					String idVehiculo = eElement
							.getElementsByTagName("idVehiculo")
							.item(0)
							.getTextContent();
					String idCliente = eElement
							.getElementsByTagName("idCliente")
							.item(0)
							.getTextContent();
					Venta venta = new Venta(idVenta, precio, fecha, idVehiculo, idCliente, idVendedor);
					response.add(venta);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("El archivo xml no se ha podido encontrar");
		}
		return response;
	}
}


