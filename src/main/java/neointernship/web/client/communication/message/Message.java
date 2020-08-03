package neointernship.web.client.communication.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements IMessage {
    private final ClientCodes clientCodes;

    public Message(@JsonProperty("messageCode") final ClientCodes clientCodes) {
        this.clientCodes = clientCodes;
    }

    public ClientCodes getClientCodes() {
        return clientCodes;
    }
}
