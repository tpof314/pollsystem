package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.dataaccess.DBConnector;
import com.dataaccess.Users;
import com.model.User;
import com.pageobject.POPollShort;

public class Main {

	public static void main(String[] args) throws JAXBException, IOException {
		/*
		 * Users users = new Users();
		 * 
		 * users.addUser(new User(10, "Ryan Heise", "blahblah",
		 * "joe@bloggs.com")); users.addUser(new User(11, "Ryan Heise2",
		 * "blahblah2", "joe2@bloggs.com"));
		 * 
		 * JAXBContext jc = JAXBContext.newInstance(Users.class); Marshaller m =
		 * jc.createMarshaller();
		 * m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 * m.marshal(users, System.out);
		 */

		
		DBConnector db = new DBConnector();
		db.setPollFile("/Users/wesley/Desktop/UTS/pollsystem/WebContent/WEB-INF/Poll.xml");
		db.setUserFile("/Users/wesley/Desktop/UTS/pollsystem/WebContent/WEB-INF/User.xml");
		
		ArrayList<POPollShort> pollList = db.getRecentPolls(); 
		System.out.println( pollList.get(0) );
	}

}
