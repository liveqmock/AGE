package com.age.core.utils;

import java.io.File;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlFormatter {
	public static String format(String unformattedXml) throws Exception {
		StringWriter out = null;
		try {
			Document doc = DocumentHelper.parseText(unformattedXml);
			OutputFormat formate = OutputFormat.createPrettyPrint();
			out = new StringWriter();
			XMLWriter writer = new XMLWriter(out, formate);
			writer.write(doc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return out.toString();
	}

	public static String formatFile(String xmlFilePath) throws Exception {
		StringWriter out = null;
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(new File(xmlFilePath));
			//OutputFormat formate = OutputFormat.createPrettyPrint();
			OutputFormat formate = OutputFormat.createCompactFormat();
			out = new StringWriter();
			XMLWriter writer = new XMLWriter(out, formate);
			writer.write(doc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return out.toString();
	}
}
