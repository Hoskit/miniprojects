import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class HttpURLConnectionExample {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://mon.trustly.com/prometheus/api/v1/query?query=trly_delayed_bank_withdrawals_count%7Bsendingecosysaccount=%27CLIENT_FUNDS_FINLAND_DABA%27,toclearinghouse=%22IBAN%22%7D";

    public static void main(String[] args) throws IOException {

        sendGET();
        System.out.println("GET DONE");
    }

    private static void sendGET() throws IOException {
        final URL               obj = new URL(GET_URL);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        final int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String             inputLine;
            final StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response);
            Gson g = new Gson();
        }
        else {
            System.out.println("GET request not worked");
        }
    }
}