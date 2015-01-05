package com.age.back.utils;

import java.util.ArrayList;
import java.util.List;

public class XmlDocument {
	private String iXMLString = "";

	public XmlDocument() {
	}

	public XmlDocument(String aXMLString) {
		this.iXMLString = aXMLString;
	}

	@Override
	public String toString() {
		return this.iXMLString;
	}

	public String toXMLString() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + this.iXMLString;
	}

	public XmlDocument getXmlDocument(String aTag) {
		XmlDocument tXmlDocument = null;
		int tStartIndex = this.iXMLString.indexOf("<" + aTag.trim() + ">");
		int tEndIndex = this.iXMLString.indexOf("</" + aTag.trim() + ">");
		if ((tStartIndex >= 0) && (tEndIndex >= 0) && (tStartIndex < tEndIndex))
			tXmlDocument = new XmlDocument(this.iXMLString.substring(tStartIndex + aTag.length() + 2, tEndIndex));
		return tXmlDocument;
	}

	public String getValueNoNull(String aTag) {
		String tValue = "";
		XmlDocument tXML = getXmlDocument(aTag);
		if (tXML != null)
			tValue = tXML.toString();
		return tValue;
	}

	public List<XmlDocument> getDocuments(String aTag) {
		String tXMLString = this.iXMLString;
		List<XmlDocument> tValues = new ArrayList<XmlDocument>();
		while (true) {
			XmlDocument tXmlDocument = null;
			int tStartIndex = tXMLString.indexOf("<" + aTag.trim() + ">");
			int tEndIndex = tXMLString.indexOf("</" + aTag.trim() + ">");
			if ((tStartIndex == -1) || (tEndIndex == -1))
				break;
			if (tStartIndex > tEndIndex)
				break;
			tXmlDocument = new XmlDocument(tXMLString.substring(tStartIndex, tEndIndex + aTag.length() + 3));
			tValues.add(tXmlDocument);
			tXMLString = tXMLString.substring(tEndIndex + 1);
		}
		return tValues;
	}

}
