package api.bdd.test.framework.action.body.impl;

import api.bdd.test.framework.action.body.BodyAction;
import org.apache.commons.jxpath.JXPathContext;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


@Component
public class XmlBodyAction implements BodyAction {

    public Object getValueByBodyPath(String xPath, Object xml){
        return JXPathContext
                .newContext(xml)
                .getValue(xPath);
    }

    public Object setValueByBodyPath(Object xml, String xPath, String value){
        JXPathContext context = JXPathContext.newContext(xml);
        context.setValue(xPath, value);
        return context.getContextBean();
    }

    public String body2String(Object bodyValue) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(bodyValue.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(bodyValue, stringWriter);
            xmlString = stringWriter.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }
}
