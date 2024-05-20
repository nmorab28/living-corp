package co.edu.unbosque.livingcorp.service;

import co.edu.unbosque.livingcorp.model.dto.ServiceProviderDTO;
import co.edu.unbosque.livingcorp.model.exception.ErrorAdditionalServiceException;
import com.google.gson.Gson;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

@Stateless
public class ProviderApiService {

    private final Client client;
    private final Gson gson;
    private static final String ApiUrl = "http://Localhost:8888/additional/services/api";

    public ProviderApiService() {
        client = ClientBuilder.newClient();
        gson = new Gson();
    }

    public List<ServiceProviderDTO> loadProviders(){
        Response response = client.target(ApiUrl).path("/provider").request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()){
            ServiceProviderDTO[] providersArray = gson.fromJson(response.readEntity(String.class), ServiceProviderDTO[].class);
            return Arrays.asList(providersArray);
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new ErrorAdditionalServiceException("Proveedores no encontrados. Status: (" + response.getStatus() + ")");
        }
        throw new ErrorAdditionalServiceException("El servicio no esta disponible. Status: (" + response.getStatus() + ")");

    }

    public ServiceProviderDTO searchProviders(int id){
        String apiUrlWithId = String.format("%s/provider/%d", ApiUrl, id);
        Response response = client.target(apiUrlWithId).request(MediaType.APPLICATION_JSON_TYPE).get();
        if(response.getStatus() == Response.Status.OK.getStatusCode()){
            return gson.fromJson(response.readEntity(String.class), ServiceProviderDTO.class);
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new ErrorAdditionalServiceException("Proveedor no encontrado. Status: (" + response.getStatus() + ")");
        }
        throw new ErrorAdditionalServiceException("El servicio no esta disponible. Status: (" + response.getStatus() + ")");
    }
}
