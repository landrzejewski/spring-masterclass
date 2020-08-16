package pl.training.bank.api.mapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Produces(BinaryMapper.MEDIA_TYPE)
@Consumes(BinaryMapper.MEDIA_TYPE)
@Provider
public class BinaryMapper implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

    public static final String MEDIA_TYPE = "application/binary-data";

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.toString().equalsIgnoreCase(MEDIA_TYPE);
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        Object result = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(entityStream)) {
            result = objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.toString().equalsIgnoreCase(MEDIA_TYPE);
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(entityStream)) {
            objectOutputStream.writeObject(object);
        }
    }

}
