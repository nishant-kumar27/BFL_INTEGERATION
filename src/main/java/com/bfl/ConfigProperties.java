package com.bfl;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class ConfigProperties {

	private final Properties props = new Properties();
	private static volatile ConfigProperties instance;

	public static ConfigProperties getInstance() throws IOException {
		if (instance == null) {
			instance = new ConfigProperties();
		}
		return instance;
	}
	
	private ConfigProperties() throws IOException {
		this.reload("application.properties");
		String profile = getConfigValue("spring.profiles.active");
		String propertiesFile = String.valueOf(System.getProperty("user.dir")) + File.separator + "config" + File.separator;
		if(profile.equalsIgnoreCase("dev"))
//			this.reloadDevFile(propertiesFile + "application-dev.properties");
			this.reload("application-dev.properties");
		else if(profile.equalsIgnoreCase("uat"))
			this.reloadDevFile(propertiesFile + "application-uat.properties");
		else if(profile.equalsIgnoreCase("prod"))
			this.reloadDevFile(propertiesFile + "application-prod.properties");
	}
	
	public String getConfigValue(String key) {
		return props.getProperty(key);
	}

	public void reload(String propsFileName) throws IOException {
		InputStream in = null;
		try {
			in = this.getClass().getClassLoader().getResourceAsStream(propsFileName);
			if (in == null) {
				throw new RuntimeException("Error: " + propsFileName + " not found in class.path="
						+ System.getProperty("java.class.path") + ".");
			}
			URL url = this.getClass().getResource("/" + propsFileName);
			String loadFileFromMsg = propsFileName + " loaded from " + url;
			System.out.println(loadFileFromMsg);
			this.props.load(in);
			Set<Object> keys = this.props.keySet();
			Iterator<Object> i = (new TreeSet<Object>(keys)).iterator();
			while (i.hasNext()) {
				i.next();
			}
		} catch (Exception var12) {
			throw new IOException("ERROR: Can't load properties from \"" + propsFileName + "(the directory it is in should be in " + "the CLASSPATH)." + var12.toString() + ".");
		} finally {
			this.closeResource(in);
		}
	}
	
	@SuppressWarnings("unused")
	public void reloadDevFile(String propsFileName) throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream(new File(propsFileName));
			if (in == null) {
				throw new RuntimeException("Error: " + propsFileName + " not found in class.path="
						+ System.getProperty("java.class.path") + ".");
			}
			this.props.load(in);
			Set<Object> keys = this.props.keySet();
			Iterator<Object> i = (new TreeSet<Object>(keys)).iterator();
			while (i.hasNext()) {
				i.next();
			}
		} catch (Exception var12) {
			throw new IOException("ERROR: Can't load properties from \"" + propsFileName + "(the directory it is in should be in " + "the CLASSPATH)." + var12.toString() + ".");
		} finally {
			this.closeResource(in);
		}
	}

	private void closeResource(Closeable closeableResource) {
		try {
			if (closeableResource != null) {
				closeableResource.close();
			}
		} catch (Exception var3) {

		}
	}
}