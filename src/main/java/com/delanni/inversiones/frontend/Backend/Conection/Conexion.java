/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.delanni.inversiones.frontend.Backend.Conection;

//import com.delanni.pos.inventoryfrontend.App;
//import com.sun.net.httpserver.Authenticator;
import com.delanni.inversiones.frontend.App;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Jesusecm
 */
public class Conexion {

    private final String URL = App.AppIP;
    private HttpURLConnection http = null;
    private String token = "";
    private URL url = null;
    private static int status = 0;
    private StringBuilder build;
    private String OutJSON;
    public static Peticion ultima;

    public HttpURLConnection HttpGet(String snd, boolean input, boolean output, boolean auth) throws IOException {
        url = new URL(URL.concat(snd));
        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        paramHeaders();
        setAuth(auth);
        this.http.setDoOutput(output);
        this.http.setDoInput(input);
        return this.http;
    }

    public HttpURLConnection HttpPost(String snd, boolean input, boolean output, boolean auth, String body) throws IOException {
        url = new URL(URL.concat(snd));
        http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");

        paramHeaders();
        this.http.setRequestProperty("Content-Type", "application/json");
        this.http.setDoInput(input);
        this.http.setDoOutput(output);
        this.OutJSON = body;
        setAuth(auth);
        return this.http;
    }

    private void paramHeaders() throws ProtocolException {
        http.setRequestProperty("os.name", System.getProperty("os.name"));
        http.setRequestProperty("user.timezone", System.getProperty("user.timezone"));
        if (App.authinfo == null) {
            http.setRequestProperty("user", "guest");
        } else {
            http.setRequestProperty("user", App.authinfo.getUsername());
        }

    }

    public Conexion() {
        /*      if (App.authinfo != null) {
            token = App.authinfo.getAccess_token();
        }*/

    }

    private void setAuth(boolean jwt) {
        if (jwt) {
            if (App.authinfo != null) {
                http.setRequestProperty("Authorization", "Bearer ".concat(App.authinfo.getAccess_token()));
            } else {
                String encoding = Base64.getEncoder().encodeToString(("admin" + ":" + "12345").getBytes());
                http.setRequestProperty("Authorization", "Basic ".concat(encoding));
            }

        } else {
            String encoding = Base64.getEncoder().encodeToString(("admin" + ":" + "12345").getBytes());
            http.setRequestProperty("Authorization", "Basic ".concat(encoding));
        }
    }

    private void getToken() {

    }

    public URL getUrl() {
        return url;
    }

    public void clearConnection() {
        http.disconnect();

    }

    public void conectar() {
        try {
            if (this.http.getDoOutput() && this.OutJSON != null) {
                reqout();
            }

            this.http.connect();
            status = this.http.getResponseCode();
            if (this.http.getDoInput()) {
                this.build = new StringBuilder();
                this.reqinput();
            }
        } catch (IOException ex) {
            //  Conexion.setStatus(HttpStatus.NOTFOUND);
            ex.printStackTrace();

        } catch (Exception ex) {
            //   Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void reqout() throws Exception {
        try (OutputStream stream = this.http.getOutputStream()) {
            byte[] salida = this.OutJSON.getBytes(StandardCharsets.UTF_8);
            stream.write(salida, 0, salida.length);
        } catch (IOException ex) {

        }
        //
    }

    public void reqinput() throws IOException {
        if (status == HttpsURLConnection.HTTP_OK) {
            Scanner scan = new Scanner(this.http.getInputStream());
            while (scan.hasNext()) {
                this.build.append(scan.nextLine());
            }
        } else {
            System.out.println(this.http.getErrorStream());
            Scanner scan1 = new Scanner(this.http.getErrorStream());
            while (scan1.hasNext()) {
                this.build.append(scan1.nextLine());
            }
        }

    }

    public StringBuilder getBuild() {
        return build;
    }

    public void setBuild(StringBuilder build) {
        this.build = build;
    }

    private void cookies() {
        String COOKIES_HEADER = "Set-Cookie";

        CookieManager msCookieManager = new java.net.CookieManager();

        Map<String, List<String>> headerFields = http.getHeaderFields();

        List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);

        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
                msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
            }
        }
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        Conexion.status = status;
    }

    public String getOutJSON() {
        return OutJSON;
    }

    public void setOutJSON(String OutJSON) {
        this.OutJSON = OutJSON;
    }

    public static List<String> getErrors() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> responses = mapper.readValue(ultima.getCuerpo().get("response"), Map.class);
            return (List<String>) responses.get("errors");
        } catch (JsonProcessingException ex) {
            return null;
        }

    }

}
