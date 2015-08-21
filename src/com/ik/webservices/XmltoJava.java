package com.ik.webservices;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;



public class XmltoJava {

	public static void main(String[] args)
	{
		try
		{
			File file = new File("D:\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			javax.xml.bind.Unmarshaller um = jaxbContext.createUnmarshaller();
			Object customer = um.unmarshal(file);
			System.out.println(customer);
		}
		catch(JAXBException jex)
		{
			jex.printStackTrace();
		}
	}
}
