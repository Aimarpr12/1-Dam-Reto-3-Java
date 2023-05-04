package xmlParser;



import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InputVentas {

	public void inputVentas() {

		try {
			File inputFile = new File("datos.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("venta");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Venta id: " 
							+ eElement.getAttribute("id"));
					System.out.println("idVendedor : " 
							+ eElement
							.getElementsByTagName("idVendedor")
							.item(0)
							.getTextContent());
					System.out.println("Bastidor : " 
							+ eElement
							.getElementsByTagName("idVehiculo")
							.item(0)
							.getTextContent());
					System.out.println("DNI Cliente : " 
							+ eElement
							.getElementsByTagName("idCliente")
							.item(0)
							.getTextContent());
					System.out.println("Precio : " 
							+ eElement
							.getElementsByTagName("precio")
							.item(0)
							.getTextContent());
					System.out.println("Fecha de venta: " 
							+ eElement
							.getElementsByTagName("fecha")
							.item(0)
							.getTextContent());
				}
			}
			System.out.println(nList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


