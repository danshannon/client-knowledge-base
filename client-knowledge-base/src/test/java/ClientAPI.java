import com.github.danshannon.ckb.model.Client;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClientAPI {
	@POST("/client")
	public Client createClient(@Body Client client);

	@GET("/client/{id}")
	public Client getClient(@Path("id") Long id);

	@PUT("/client/{id}")
	public Client updateClient(@Path("id") Long id, @Body Client client);

	@DELETE("/client/{id}")
	public Client deleteClient(@Path("id") Long id);

	@PATCH("/client/{id}")
	public Client patchClient(@Path("id") Long id, @Body Client client);

}
