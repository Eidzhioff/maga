package ru.unecon.dom;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomXmlProcessor {

    public void processXml() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("unecon.xml");
            if (inputStream == null) {
                throw new FileNotFoundException("Файл unecon.xml не найден в resources");
            }

            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            NodeList subjectList = document.getElementsByTagName("subject");
            int modifiedCount = 0;

            for (int i = 0; i < subjectList.getLength(); i++) {
                Element subject = (Element) subjectList.item(i);
                String hoursStr = subject.getAttribute("hours");

                try {
                    int hours = Integer.parseInt(hoursStr);
                    if (hours < 100) {
                        subject.setAttribute("hours", "100");
                        modifiedCount++;

                        String subjectName = subject.getAttribute("name");
                        System.out.println("Изменен предмет: " + subjectName +
                                " (было: " + hours + " часов, стало: 100 часов)");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка парсинга hours: " +
                            subject.getAttribute("name"));
                }
            }

            System.out.println("Всего изменено предметов: " + modifiedCount);

            saveDocument(document);

        } catch (Exception e) {
        }
    }

    private void saveDocument(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(document);

        File outputFile = new File("unecon_fixed.xml");
        StreamResult result = new StreamResult(outputFile);

        transformer.transform(source, result);

        System.out.println("Измененный XML сохранен в: " + outputFile.getAbsolutePath());
    }
}