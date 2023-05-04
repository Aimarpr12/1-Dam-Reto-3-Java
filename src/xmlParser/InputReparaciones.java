package xmlParser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InputReparaciones {

	public void inputReparaciones() {

		try {
			File inputFile = new File("datos.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("reparacion");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("Reparacion id: " 
							+ eElement.getAttribute("id"));
					System.out.println("idMecanico : " 
							+ eElement
							.getElementsByTagName("idMecanico")
							.item(0)
							.getTextContent());
					System.out.println("Descripción : " 
							+ eElement
							.getElementsByTagName("descripcion")
							.item(0)
							.getTextContent());
					System.out.println("Coste : " 
							+ eElement
							.getElementsByTagName("coste")
							.item(0)
							.getTextContent());
					System.out.println("Precio : " 
							+ eElement
							.getElementsByTagName("precio")
							.item(0)
							.getTextContent());
					System.out.println("Fecha de inicio : " 
							+ eElement
							.getElementsByTagName("fechaIni")
							.item(0)
							.getTextContent());
					System.out.println("Fecha fin : " 
							+ eElement
							.getElementsByTagName("fechaFin")
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