package com.example.electronicshop.security;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionReader {
    /**
     * Read configuration file
     * @param securityFile - configuration file
     * @return - map where key a user role and value list of security url
     */
    public Map<String, List<String>> readFile(String securityFile){
        File xmlFile = new File(securityFile);
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
        }catch (ParserConfigurationException | IOException | SAXException ex){
            ex.printStackTrace();
        }
        assert doc != null;
        return getPermission(doc);
    }

    private Map<String, List<String>> getPermission(Document doc) {
        NodeList nodeList = doc.getElementsByTagName("constraint");
        Map<String, List<String>> securityPermissions = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            List<String> roleList = new ArrayList<>();
            Node constraintNode = nodeList.item(i);
            if (constraintNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) constraintNode;
                String urlPattern = element.getElementsByTagName("url-pattern").item(0).getTextContent();
                NodeList roles = element.getElementsByTagName("role");
                setRolePermission(roles,roleList,element,urlPattern,securityPermissions);
            }
        }
        return securityPermissions;
    }
    private void setRolePermission(NodeList roles, List<String> roleList, Element element,
                                   String urlPattern, Map<String, List<String>> securityPermissions){
        for (int j = 0; j < roles.getLength(); j++) {
            roleList.add(element.getElementsByTagName("role").item(j).getTextContent());
            String role = element.getElementsByTagName("role").item(j).getTextContent();
            if (securityPermissions.get(role) != null) {
                List<String> urlList = securityPermissions.get(role);
                urlList.add(urlPattern);
                securityPermissions.put(role, urlList);
            } else {
                List<String> urlList = new ArrayList<>();
                urlList.add(urlPattern);
                securityPermissions.put(role, urlList);
            }
        }
    }
}
