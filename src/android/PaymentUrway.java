package cordova.plugin.payment;

import org.apache.cordova.CordovaPlugin;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class echoes a string called from JavaScript.
 */
public class PaymentUrway extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if(action.equals("payment"))
        {
            this.payment(args,callbackContext);
            return true;
        }
        return false;
    }

    
    private void payment(JSONArray args,CallbackContext callbackContext)
    {
        if(args != null)
        {
// String rquest= {"terminalId":"iOSAndTerm","password":"password","action":"1","currency":"SAR",
// "customerEmail":"wrere@sdas.asd","address":"ddasd","city":"asdasd","zipCode":"234555"
// ,"country":"IN","amount":"34.00","state":"sadsa","customerIp":"10.0.2.16",
// "merchantIp":"10.11.11.12","tranid":"2013302158922905368","trackid":"326051","udf1":"","udf2":"","udf3":"","udf4":"","udf5":"dgdfgdf","udf7":"ANDROID"};

              
            try
            {
                String p2=args.getJSONObject(0).getString("param2");
                JSONObject jsondata = new JSONObject();
                jsondata=createJson(args);
               String hashValue = generateHashKey(jsondata, "146c0c30025cadba9fbdf9e909b49eac1b631b4afeb56485f93d8f271a832e3a");

               


                StringBuffer response = new StringBuffer();
                URL obj = new URL("https://payments-dev.urway-tech.com/URWAYPGService/transaction/jsonProcess/JSONrequest");
                // System.out.println("configured url:" + requesturl);
                jsondata.put("requestHash", hashValue);
                // System.out.println("HashValue" + hashValue);
                // System.out.println("JSON REQ HASHVAL" + jsondata.getString("requestHash"));
                // System.out.println("json request is" + jsondata);
               
                HttpURLConnection httpCon = (HttpURLConnection) obj.openConnection();
               
                httpCon.setDoOutput(true);
                httpCon.setRequestMethod("POST");
                httpCon.setRequestProperty("Content-Type", "application/json");
                httpCon.setRequestProperty("Accept", "application/json");
                
                OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
                out.write("jsondata.toString()");
                out.flush();
                out.close();
                BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    response.append(inputLine);
                }
                in.close();
                System.out.println("response string is" + response.toString());
                callbackContext.success("RESULT is "+p2+""+ response.toString());
        
                    
                  
            }
            catch(Exception ex)
            {
                callbackContext.error("Exception "+ ex);
            }
        }
        else
        {
            callbackContext.error("Please pass some value");
        }

    }

    public JSONObject createJson(JSONArray args)
    {

JSONObject testJson = new JSONObject();

try {
    testJson.put("terminalId", "tokenter");
    testJson.put("password", "password");
    testJson.put("action", "1");
    testJson.put("currency", "SAR");
//     if (email == null || "".equalsIgnoreCase(email)) {
// //                testJson.put("customerEmail", "a@test.com");
//         testJson.put("customerEmail", "");
//     } else {
        testJson.put("customerEmail", "runa187@sdf.com");
    // }
    testJson.put("address", "Shiv Mandir");
    testJson.put("city", "Dombivli");
    testJson.put("zipCode", "123456");
    testJson.put("country", "IN");
    testJson.put("amount", args.getJSONObject(0).getString("amount"));
    // amount = Amount;
    // merchantKey=merchantk;
    testJson.put("state", "mahareashta");

    // if (customer_Ip == null || "".equalsIgnoreCase(customer_Ip)) {

    //     try {
    //         testJson.put("customerIp", Inet4Address.getLocalHost().getHostAddress());
    //     } catch (UnknownHostException e) {
           
    //         e.printStackTrace();
    //     }
    // } else {

        testJson.put("customerIp", "1.1.1.1");
    // }
    // if (merchant_Ip == null || "".equalsIgnoreCase(merchant_Ip)) {

    //     try {
    //         testJson.put("merchantIp", Inet4Address.getLocalHost().getHostAddress());
    //     } catch (UnknownHostException e) {
            
    //         e.printStackTrace();
    //     }
    // } else {

        testJson.put("merchantIp", "1.2.34.4");
    // }


    // if (("1").equalsIgnoreCase(action_Code) || ("4").equalsIgnoreCase(action_Code)) {
    //     if (trans_Id.length() > 18) {

    //         testJson.put("transid", trans_Id);
    //     } else {

    //         testJson.put("tranid", generateTranId());
    //     }
    // } else {
    //     testJson.put("transid", trans_Id);
    // }
    // String trackID = getRandomNumberString();
    // Log.e("Response trackID:", trackID);
    testJson.put("trackid", "1234");

    testJson.put("udf1", "usr_Fld1");
    testJson.put("udf2", "");
    testJson.put("udf3", "");

    testJson.put("udf4", "");
    testJson.put("udf5", "");
    testJson.put("udf10", "");

}
catch (JSONException e1)
{
    e1.printStackTrace();
}
return testJson;


}

public String generateHashKey(JSONObject jsonObj, String merchantKey)
{
    String pipeSeperatedString = "";
    String hashKey = null;
    Sha1Encryption hash = new Sha1Encryption();
    try {
        try
        {
            pipeSeperatedString = jsonObj.get("trackid") + "|" + jsonObj.get("terminalId") + "|" + jsonObj.get("password") + "|" + "146c0c30025cadba9fbdf9e909b49eac1b631b4afeb56485f93d8f271a832e3a" + "|" + jsonObj.get("amount") + "|" + jsonObj.get("currency");
        }   catch (JSONException e)
        {
            
            e.printStackTrace();
        }
            hashKey = hash.SHA256(pipeSeperatedString);

    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        
        e.printStackTrace();
    }
    return hashKey;
}

//    public void sendTrans(JSONArray args,CallbackContext callbackContext) throws Exception {
       
//     StringBuffer response = new StringBuffer();
//         URL obj = new URL("https://payments-dev.urway-tech.com/URWAYPGService/transaction/jsonProcess/JSONrequest");
//         System.out.println("configured url:" + requesturl);
//         jsondata.put("requestHash", hashValue);
//         System.out.println("HashValue" + hashValue);
//         System.out.println("JSON REQ HASHVAL" + jsondata.getString("requestHash"));
//         System.out.println("json request is" + jsondata);
       
//         HttpURLConnection httpCon = (HttpURLConnection) obj.openConnection();
       
//         httpCon.setDoOutput(true);
//         httpCon.setRequestMethod("POST");
//         httpCon.setRequestProperty("Content-Type", "application/json");
//         httpCon.setRequestProperty("Accept", "application/json");
//         OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
//         out.write(jsondata.toString());
//         out.flush();
//         out.close();
//         BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
//         String inputLine;
//         while ((inputLine = in.readLine()) != null) {
//             System.out.println(inputLine);
//             response.append(inputLine);
//         }
//         in.close();
//         System.out.println("response string is" + response.toString());
//         callbackContext.success("RESULT is "+ response.toString());

// //Req JSON
// //{"terminalId":"iOSAndTerm","password":"password","action":"1","currency":"SAR",
// //"customerEmail":"wrere@sdas.asd","address":"ddasd","city":"asdasd","zipCode":"234555"
// //,"country":"IN","amount":"34.00","state":"sadsa","customerIp":"10.0.2.16",
// //"merchantIp":"10.11.11.12","tranid":"2013302158922905368","trackid":"326051","udf1":"","udf2":"","udf3":"","udf4":"","udf5":"","udf7":"ANDROID"}

//     }
class Sha1Encryption {
    public byte[] hash(String text)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md =  MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("utf-8"));
        byte[] md5 = md.digest();
        return md5;
    }

    public String SHA384(String text)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-384");
        md.update(text.getBytes("utf-8"));
        byte[] sha384 = md.digest();
        return String.valueOf(Hex.encodeHex(sha384));
    }

    public String SHA256(String text)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes("utf-8"));
        byte[] sha384 = md.digest();
        return String.valueOf(Hex.encodeHex(sha384));
    }
}
}