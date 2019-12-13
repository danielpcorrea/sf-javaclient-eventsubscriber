/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app.infrastructure.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.enterprise.inject.Default;
 
@Default
public class PropertyFileReader implements IPropertyFileReader
{
	private Properties prop;
	private InputStream input;
	private Enumeration<?> allProperties;
	private String pathToFile;

	public PropertyFileReader() {
		this.pathToFile = "config.properties";
	}
	public PropertyFileReader(String paPropertyFileName) {
		this.pathToFile = paPropertyFileName;
	}
	public String getProperty(String paKey)
	{
		loadProperties(); 
		String propertieValue = "";
		if(allProperties != null)
		{
			String key = "",value = "";
			while (allProperties.hasMoreElements()) {
				key = (String) allProperties.nextElement();
				value = prop.getProperty(key);
				if(key.equalsIgnoreCase(paKey))
				{
					propertieValue = value;
					break;
				} 
			}
			}
		return propertieValue;		
	}
	private void loadProperties() 
	{ 
		prop = new Properties();
		input = null;
		try {

			String filename = pathToFile;
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input != null) 
			{
				prop.load(input);
				allProperties = prop.propertyNames();
			}	

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}