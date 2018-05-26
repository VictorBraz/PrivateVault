package main.java;

import java.io.Serializable;

/**
 * @author Victor Machado Braz.
 * Created on 12/05/2018.
 */
public class PrivateKeyModel implements Serializable {
    private String privateKey;
    private String coin;

    public void PrivateKeyModel(String privateKey, String coin){
        this.privateKey = privateKey;
        this.coin = coin;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
