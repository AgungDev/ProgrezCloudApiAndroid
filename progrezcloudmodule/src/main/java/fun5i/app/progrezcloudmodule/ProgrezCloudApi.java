package fun5i.app.progrezcloudmodule;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import fun5i.app.progrezcloudmodule.Model.*;

/**
 *
 * @author fun5i
 */
public class ProgrezCloudApi {

    private static final String TAG = "ProgrezCloudApi";

    @FunctionalInterface
    public interface ProjectCallback{
        void responseProject(int errno2, String errmsg2, String body);
    }

    @FunctionalInterface
    public interface LoginCallback{
        void responseLogin(int errno, String errmsg, PCLoginModel account);
    }

    public ProgrezCloudApi(){
    }

    private String generatePayload(String tokenProject, String fields){
        String result = null;
        try {
            JSONObject payload = new JSONObject();
            JSONObject payload2 = new JSONObject();
            JSONObject payload3 = new JSONObject();

            payload3.put("fields", fields);
            payload2.put("maintask", payload3);
            payload2.put("task", payload3);
            payload2.put("subtask", payload3);
            payload.put("tasks", payload2);
            payload.put("token", tokenProject);

            result = payload.toString();
        }catch (JSONException e){
            e.printStackTrace();
        }

        return result;
    }

    public void setProject(PCLoginModel account,ProjectCallback abc, String tokenProject, String fields) {
        String payload = generatePayload(tokenProject, fields);
        ConnectionMethod loginMethod = new ConnectionMethod();
        loginMethod.setAccount(account);
        loginMethod.execute("project", payload);
        loginMethod.responds((String body) ->{
            try{
                JSONObject res = new JSONObject(body);
                if(res.getInt("errno")>0)
                    abc.responseProject(
                            res.getInt("errno"),
                            res.getString("errmsg"),
                            res.toString());
                else
                    abc.responseProject(
                            res.getInt("errno"),
                            res.getString("errmsg"),
                            res.getJSONObject("data").toString());
            }catch (JSONException e){
                e.printStackTrace();
            }
        });
    }



    public void login(LoginCallback a, String username, String password) {
        ConnectionMethod loginMethod = new ConnectionMethod();
        loginMethod.execute("login","login="+username+"&password="+password);
        loginMethod.responds((String body) -> {
                    try{
                        JSONObject respond = new JSONObject(body);
                        if(respond.getInt("errno")>0)
                            a.responseLogin(
                                respond.getInt("errno"),
                                respond.getString("errmsg"),
                                null);
                        else
                            a.responseLogin(
                                    respond.getInt("errno"),
                                    respond.getString("errmsg"),
                                    generateAccount(respond));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
        );

    }

    public void login(LoginCallback a, String userkey) {
            ConnectionMethod loginMethod = new ConnectionMethod();
            loginMethod.execute("login","type=userkey&userkey="+userkey);
            loginMethod.responds((String body) -> {
                    try{
                        JSONObject respond = new JSONObject(body);
                        if(respond.getInt("errno")>0)
                            a.responseLogin(
                                    respond.getInt("errno"),
                                    respond.getString("errmsg"),
                                    null);
                        else
                            a.responseLogin(
                                    respond.getInt("errno"),
                                    respond.getString("errmsg"),
                                    generateAccount(respond));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            );
    }

    private PCLoginModel generateAccount(JSONObject respond) {
        PCLoginModel pcLogin = null;
        try {
            JSONObject resCrident = respond.getJSONObject("credentials");
            pcLogin = new PCLoginModel(
                    respond.getString("flying_id"),
                    respond.getString("fullname"),
                    respond.getString("photo"),
                    new PCCredentials(
                            resCrident.getString("d"),
                            resCrident.getString("s"),
                            resCrident.getString("o")
                    )

            );
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return pcLogin;
    }


    static class ConnectionMethod extends AsyncTask<String, String, String> {
        interface onCallback {
            void respond(String body);
        }
        private String body;

        onCallback onCallback;
        void responds(onCallback a){
            onCallback=a;
        }

        PCLoginModel accon;
        void setAccount(PCLoginModel ac){
            this.accon = ac;
        }

        @Override
        protected String doInBackground(String... parms) {
            if (parms[0].equals("login")){
                body = actLogin(parms[1]);
            }else if(parms[0].equals("project")){
                body = actProject(accon, parms[1]);
            }
            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onCallback.respond(s);
        }

        private String actLogin(String urlParameters){
            String result = null;
            URL url;
            HttpURLConnection conn = null;
            try {
                byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
                int    postDataLength = postData.length;
                String request        = "https://progrez.cloud/s/fox/login";
                url            = new URL( request );
                conn= (HttpURLConnection) url.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty( "charset", "utf-8");
                conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
                try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                    wr.write( postData );
                    wr.flush();
                }

                StringBuilder sb = new StringBuilder();
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    sb.append(line);
                }
                result = sb.toString();


            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(conn != null) // Make sure the connection is not null.
                    conn.disconnect();
            }

            return result;
        }

        private String actProject(PCLoginModel accouts, String payload){
            String result = null;
            PCCredentials credentials = accouts.getCredentials();
            try {
                byte[] postData       = payload.getBytes( StandardCharsets.UTF_8 );
                int    postDataLength = postData.length;
                String request        = "https://progrez.cloud/s/fox/project";
                URL    url            = new URL( request );
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                conn.setRequestMethod( "POST" );

                JSONObject crFox = new JSONObject();
                crFox.put("d", credentials.getD());
                crFox.put("s", credentials.getS());
                crFox.put("o", credentials.getO());

                conn.setRequestProperty( "Credential-Fox", crFox.toString());
                conn.setRequestProperty( "charset", "utf-8");
                conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
                try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                    wr.write( postData );
                }

                StringBuilder sb = new StringBuilder();
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    sb.append(line);
                }
                result = sb.toString();
            }catch(IOException|JSONException e){
                e.printStackTrace();
            }

            return result;
        }

    }

}