package yrw.utils;

import org.codehaus.jackson.map.ObjectMapper;
import yrw.model.AbstractEntity;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;


@FacesConverter(value = "jsonEntityConverter", forClass = AbstractEntity.class)
public class JsonEntityConverter implements Converter {
    private static final Logger LOGGER = Logger.getLogger(JsonEntityConverter.class.getName());
    private static final String CLASS_SEPARATOR = ":::";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String stringValue) {
        if (stringValue.isEmpty()) {
            return null;
        }

        String[] parts = stringValue.split(CLASS_SEPARATOR);
        if (parts.length != 2) {
            throw new ConverterException(stringValue + " cannot be parsed as Entity");
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(Base64.getDecoder().decode(parts[1]), Class.forName(parts[0]));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error parsing value: " + stringValue, e);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if (object == null) {
            return "";
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return object.getClass().getName() + CLASS_SEPARATOR + Base64.getEncoder().encodeToString(mapper.writeValueAsString(object).getBytes());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading object: " + object, e);
        }
        return null;
    }
}
