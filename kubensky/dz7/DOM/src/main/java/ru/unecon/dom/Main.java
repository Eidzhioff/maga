package ru.unecon.dom;

public class Main {
    public static void main(String[] args) {
        System.out.println("Обработка XML файла unecon.xml...");

        DomXmlProcessor processor = new DomXmlProcessor();
        processor.processXml();

        System.out.println("Обработка завершена!");
    }
}