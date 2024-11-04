package com.jn.bktravels.Config;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Signature {

    public  String secretHashGenerator(float totalAmount, String transactionUuid) {


        try {
            SecurityConfig config = new SecurityConfig();
            String secret = config.getSecretKey();
            MerchantKey merchantKey = new MerchantKey();
            String productCode = merchantKey.getMerchantKey();
            System.out.println(secret);
            String message = "total_amount="+totalAmount+",transaction_uuid="+transactionUuid+",product_code="+productCode;
            System.out.println(message);

            // Message to be hashed
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
            sha256_HMAC.init(secret_key);
            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));

            System.out.println(hash);
            return hash;
        } catch (Exception e) {
            e.printStackTrace();  // Logging the exception for better debugging
            return null;
        }
    }
}
