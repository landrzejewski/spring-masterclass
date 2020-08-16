package pl.training.bank.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import pl.training.bank.api.mapper.BinaryMapper;
import pl.training.bank.client.soap.AccountDto;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    public static void main(String[] args) {
        ResteasyClient client = new ResteasyClientBuilder()
                .register(BinaryMapper.class)
                .build();
        ResteasyWebTarget accounts = client.target("http://localhost:8080/bank/api/v1/accounts");

        Response response = accounts.request()
                //.accept(MediaType.APPLICATION_JSON_TYPE)
                .accept(BinaryMapper.MEDIA_TYPE)
                .post(null);
        System.out.println("New account created: " + response.getLocation());
        System.out.println(response.readEntity(AccountDto.class));

        System.out.println(client.target("http://localhost:8080/bank/api/v1/accounts/1")
            .request(MediaType.APPLICATION_JSON_TYPE).get().readEntity(String.class));
    }

}
