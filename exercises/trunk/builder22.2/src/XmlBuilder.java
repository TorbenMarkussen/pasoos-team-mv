
public class XmlBuilder implements Builder {
    private StringBuilder result = new StringBuilder();

    XmlBuilder(){
        result.append("<Document>\n");
    }
    @Override
    public void buildSection(String text) {
        append("Section", text);
    }

    private void append(String elementName, String text) {
        result.append("<").append(elementName).append(">");
        result.append("<![CDATA[");
        result.append(text);
        result.append("]]>");
        result.append("</").append(elementName).append(">\n");
    }

    @Override
    public void buildSubsection(String text) {
        append("Subsection", text);
    }

    @Override
    public void buildParagraph(String text) {
        append("Paragraph", text);
    }

    public String getResult() {
        return result.toString() + "</Document>\n";
    }
}
