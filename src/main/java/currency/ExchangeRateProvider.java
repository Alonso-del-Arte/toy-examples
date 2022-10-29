package currency;

import sysops.EnvironmentVariableStore;

import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Scanner;

public class ExchangeRateProvider {

    private static final String API_KEY
            = EnvironmentVariableStore.getVariable("FOREX_API_KEY");

    private static final String USER_AGENT_ID = "Java/"
            + System.getProperty("java.version");

    static URL setupQueryURL(Currency source, Currency target)
            throws MalformedURLException {
        String urlText = "https://free.currconv.com/api/v7/convert?q="
                + source.getCurrencyCode() + "_"
                + target.getCurrencyCode() + "&compact=ultra&apiKey="
                + API_KEY;
        return new URL(urlText);
    }

    static String getNumberText(URL queryURL) throws IOException {
        HttpURLConnection connection
                = (HttpURLConnection) queryURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT_ID);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream stream = (InputStream) connection.getContent();
            Scanner scanner = new Scanner(stream);
            String quote = scanner.nextLine();
            return quote.substring(quote.indexOf(':') + 1, quote.indexOf('}'));
        } else {
            String excMsg = "Query returned status code " + responseCode;
            throw new IOException(excMsg);
        }
    }

    public static double rate(Currency source, Currency target)
            throws IOException {
        URL url = setupQueryURL(source, target);
        String numberText = getNumberText(url);
        return Double.parseDouble(numberText);
    }

}
