package neointernship.web.client.communication.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDto {
    private ClientCodes clientCodes;

    public boolean validate() throws Exception {
        if (clientCodes == null) {
            throw new Exception("Код сообщения отсутствует");
        }
        return true;
    }

    public ClientCodes getClientCodes() {
        return clientCodes;
    }
}
