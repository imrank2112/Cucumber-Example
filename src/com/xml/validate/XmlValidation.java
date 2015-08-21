package com.xml.validate;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlValidation {

	public static void main(String[] args)
	{

		DocumentBuilder parser = null;
		try {
			parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Document document = null;
		try {
			document = parser.parse(new File("D:\\Repositories\\ci_git_repos\\Functions\\activeeon\\cdpetl\\ipt\\cdp_application_data_housekeeping_batch\\install\\cdp_application_data_housekeeping_batch_workflow.xml"));
		} catch (SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// create a SchemaFactory capable of understanding WXS schemas
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// load a WXS schema, represented by a Schema instance
		Source schemaFile = new StreamSource(new File("C:\\Users\\Imran11296\\Documents\\workflow.xsd"));
		Schema schema = null;
		try {
			schema = factory.newSchema(schemaFile);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// create a Validator instance, which can be used to validate an instance document
		Validator validator = schema.newValidator();

		// validate the DOM tree
		try {
			try {
				validator.validate(new DOMSource(document));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SAXException e) {
			// instance document is invalid!
		}
	}
}
