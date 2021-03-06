package io.github.handofgod94;


public class AbstractXmlUnitTest {
  protected static final String DUMMY_URI = "dummyUri.xml";
  protected static final int DUMMY_VERSION = 1;
  protected static final String MOCK_XML_TEXT =
      "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
      + "<beans xmlns = \"http://www.springframework.org/schema/beans\"\n"
      + "\t xmlns:xsi = \"http://www.w3.org/2001/XMLSchema-instance\"\n"
      + "\t xsi:schemaLocation = \"http://www.springframework.org/schema/beans \n"
      + "\t http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\">\n"
      + "\t <bean id = \"helloWorld\" class = \"io.github.handofgod94.HelloWorld\""
      + "scope = \"prototype\"></bean>\n"
      + "</beans>";

  protected static final String MOCK_XSD_TEXT =
      "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
      + "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n"
      + "<xs:element name=\"shipto\">\n"
      + "<xs:complexType>\n"
      + "<xs:sequence>\n"
      +    "<xs:element name=\"name\" type=\"xs:string\"/>\n"
      +    "<xs:element name=\"address\" type=\"xs:string\"/>\n"
      +    "<xs:element name=\"city\" type=\"xs:string\"/>\n"
      +    "<xs:element name=\"country\" type=\"xs:string\"/>\n"
      + "</xs:sequence>\n"
      + "</xs:complexType>\n"
      + "</xs:element>"
      + "</xs:schema>";
}
