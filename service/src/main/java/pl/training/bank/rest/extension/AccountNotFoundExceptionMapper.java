package pl.training.bank.rest.extension;

import pl.training.bank.api.ExceptionDto;
import pl.training.bank.api.account.AccountNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.*;

@Provider
public class AccountNotFoundExceptionMapper implements ExceptionMapper<AccountNotFoundException> {

    private static final String DESCRIPTION = "Account not found";

    @Override
    public Response toResponse(AccountNotFoundException exception) {
        return Response.status(NOT_FOUND)
                .entity(new ExceptionDto(DESCRIPTION))
                .build();
    }

}
