package app.layerPersistenceAndDataAccess.serviceAgent.restConsumer.accountConsumer.dto;

/**
 * @author Christian G. on 02.11.2017
 */
public class RegisterUserDTO {

    private String message;

    // TODO - encryption und object
    /*
    "encryption": {
    "HMACK": "sha256",
    "algorithm": "AES",
    "encryption": "Fernet",
    "key": "++OyD40Gb2nT70ZBPZirc9OxhLK3RbXeEG7pe4uzgIA=\n",
    "key_encoding": "base64",
    "keylength": "128",
    "mode": "CBC",
    "padding": "PKCS7"
  },
  "message": "Created User",
  "object": [
    {
      "_links": {
        "encryption_key": "/users/MeinSuperTestUser/encryption_key",
        "self": "/users/MeinSuperTestUser"
      },
      "deliverables_done": [],
      "delivered": [],
      "ip": null,
      "location": null,
      "name": "MeinSuperTestUser"
    }
  ],
     */

    public RegisterUserDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
