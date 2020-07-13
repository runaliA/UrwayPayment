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

import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

                    
                    callbackContext.success("RESULT is "+p2);
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

}