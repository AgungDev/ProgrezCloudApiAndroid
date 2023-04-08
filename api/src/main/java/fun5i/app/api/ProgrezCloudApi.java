package fun5i.app.api;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import fun5i.app.api.Model.Data;
import fun5i.app.api.Model.Maintask;
import fun5i.app.api.Model.PCCredentials;
import fun5i.app.api.Model.PCLoginModel;
import fun5i.app.api.Model.PCProjectModel;
import fun5i.app.api.Model.Project;


/**
 * version 2.0.0
 * @author fun5i
 */
public class ProgrezCloudApi {

    private static final String TAG = "ProgrezCloudApi";

    // update 1.2.0
    @FunctionalInterface
    public interface ProjectCallback{
        void responseProject(int errno2, String errmsg2, PCProjectModel body);
    }

    // update 1.2.0
    @FunctionalInterface
    public interface LoginCallback{
        void responseLogin(int errno, String errmsg, PCLoginModel account);
    }

    // update 1.2.0
    public ProgrezCloudApi(){
    }

    // update 2.0.0
    private int error = 0;
    private String errorMessage = "ok";
    private String userkey;
    private PCLoginModel loginModel;
    private PCCredentials credentials;
    private Project project;
    private List<Maintask> maintasks;

    // update 2.0.0
    private void setSemuaObjectProject(PCProjectModel projectModel){
        if (Integer.parseInt(projectModel.getErrno()) != 0){
            this.error = Integer.parseInt(projectModel.getErrno());
            this.errorMessage = projectModel.getErrmsg();
        }else{
            Data data = projectModel.getData();
            this.project = data.getProject();
            this.maintasks = data.getMaintask();
        }

    }

    // update 2.0.0
    public Project getProject() {
        return project;
    }

    // update 2.0.0
    public List<Maintask> getMaintasks() {
        return maintasks;
    }

    // update 2.0.0
    public void setMaintasks(List<Maintask> maintasks) {
        this.maintasks = maintasks;
    }

    // update 2.0.0
    public String getUserkey() {
        return userkey;
    }

    public PCLoginModel getLoginModel() {
        return loginModel;
    }

    // update 2.0.0
    public void setLoginModel(PCLoginModel loginModel) {
        this.loginModel = loginModel;
    }

    // update 2.0.0
    public PCCredentials getCredentials() {
        return credentials;
    }

    // update 2.0.0
    public void setCredentials(PCCredentials credentials) {
        this.credentials = credentials;
    }

    // update 2.0.0
    public int getError() {
        return error;
    }

    // update 2.0.0
    private void setError(int error) {
        this.error = error;
    }

    // update 2.0.0
    public String getErrorMessage() {
        return errorMessage;
    }

    // update 2.0.0
    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // update 1.2.0
    private String generatePayload(String tokenProject, String[] fields){
        String result = null;
        try {
            JSONObject payload = new JSONObject();
            JSONObject payload2 = new JSONObject();

            payload2.put("maintask", new JSONObject().put("fields", fields[0]));
            payload2.put("task", new JSONObject().put("fields", fields[1]));
            payload2.put("subtask", new JSONObject().put("fields", fields[2]));

            payload.put("tasks", payload2);
            payload.put("token", tokenProject);

            result = payload.toString();
        }catch (JSONException e){
            e.printStackTrace();
        }

        return result;
    }

    // update 2.0.0
    public void setProject(String tokenProject, String[] fields){
        String payload = generatePayload(tokenProject, fields);
        ConnectionMethod connectionMethod = new ConnectionMethod();
        connectionMethod.setAccount(getLoginModel());
        connectionMethod.execute("project", payload);
        connectionMethod.responds((String body) ->{
            try{
                PCProjectModel out = null;
                JSONObject res = new JSONObject(body);
                // convert to object
                if (res.getInt("errno") == 0){
                    // setCredential
                    JSONObject crid = res.getJSONObject("credentials");
                    PCCredentials newCCredentials = new PCCredentials(
                            crid.getString("d"),
                            crid.getString("s"),
                            crid.getString("o")
                    );
                    setCredentials(newCCredentials);
                    setLoginModel(new PCLoginModel(
                            this.loginModel.getFlying_id(),
                            this.loginModel.getFullname(),
                            this.loginModel.getPhoto(),
                            newCCredentials
                    ));

                    Gson gson =new Gson();
                    out = gson.fromJson(
                            res.getJSONObject("data").toString(), PCProjectModel.class);
                    //System.out.println("Berhasil " + projectModel.getData().getMaintask().get(0).getTaskName());
                }else{
                    setError(res.getInt("errno"));
                    setErrorMessage(res.getString("errmsg"));
                }
                setSemuaObjectProject(out);
            }catch (JSONException e){
                e.printStackTrace();
            }
        });
    }

    // update 2.0.0
    public void getProject(PCLoginModel account,ProjectCallback abc, String tokenProject, String[] fields) {
        String payload = generatePayload(tokenProject, fields);
        ConnectionMethod connectionMethod = new ConnectionMethod();
        connectionMethod.setAccount(account);
        connectionMethod.execute("project", payload);
        connectionMethod.responds((String body) ->{
            try{
                JSONObject res = new JSONObject(body);
                PCProjectModel out = null;

                // convert to object
                if (res.getInt("errno") == 0){
                    // setCredential
                    JSONObject crid = res.getJSONObject("credentials");
                    PCCredentials newCCredentials = new PCCredentials(
                            crid.getString("d"),
                            crid.getString("s"),
                            crid.getString("o")
                    );
                    setCredentials(newCCredentials);
                    setLoginModel(new PCLoginModel(
                            this.loginModel.getFlying_id(),
                            this.loginModel.getFullname(),
                            this.loginModel.getPhoto(),
                            newCCredentials
                    ));

                    Gson gson =new Gson();
                    out = gson.fromJson(
                            res.getJSONObject("data").toString(), PCProjectModel.class);
                    //System.out.println("Berhasil " + projectModel.getData().getMaintask().get(0).getTaskName());
                }else{
                    setError(res.getInt("errno"));
                    setErrorMessage(res.getString("errmsg"));
                }

                // Update interface
                abc.responseProject(
                        res.getInt("errno"),
                        res.getString("errmsg"),
                        out
                );
                setSemuaObjectProject(out);
            }catch (JSONException e){
                e.printStackTrace();
            }
        });
    }

    // update 2.0.0
    public ProgrezCloudApi setUserKey(String userkey){
        this.userkey = userkey;
        ConnectionMethod loginMethod = new ConnectionMethod();
        loginMethod.execute("login","type=userkey&userkey="+userkey);
        loginMethod.responds((String body) -> {
            try {
                JSONObject respond = new JSONObject(body);
                if (respond.getInt("errno") > 0){
                    this.error = respond.getInt("errno");
                    this.errorMessage = respond.getString("errmsg");
                }else{
                    //success set loginModel and credential
                    setLoginModel(generateAccount(respond));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        });
        return this;
    }

    //update 2.0.0
    public void login(LoginCallback a, String username, String password) {
        ConnectionMethod loginMethod = new ConnectionMethod();
        loginMethod.execute("login","login="+username+"&password="+password);
        loginMethod.responds((String body) -> {
                    try{
                        JSONObject respond = new JSONObject(body);
                        PCLoginModel abc = null;
                        if(respond.getInt("errno") == 0){
                            abc = generateAccount(respond);
                            setLoginModel(abc);
                            setCredentials(abc.getCredentials());
                        }

                        a.responseLogin(
                                respond.getInt("errno"),
                                respond.getString("errmsg"),
                                (respond.getInt("errno") > 0)?null:abc
                        );
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
        );
    }

    // update 2.0.0
    public void loadNewCridential(){
        ConnectionMethod loginMethod = new ConnectionMethod();
        loginMethod.execute("login","type=userkey&userkey="+userkey);
        loginMethod.responds((String body) -> {
            try{
                JSONObject respond = new JSONObject(body);
                if (respond.getInt("errno") > 0){
                    this.error = respond.getInt("errno");
                    this.errorMessage = respond.getString("errmsg");
                }else{
                    //success set loginModel and credential
                    setLoginModel(generateAccount(respond));
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        });
    }

    //update 2.0.0
    public void login(LoginCallback a, String userkey) {
            ConnectionMethod loginMethod = new ConnectionMethod();
            loginMethod.execute("login","type=userkey&userkey="+userkey);
            loginMethod.responds((String body) -> {
                    try{
                        JSONObject respond = new JSONObject(body);
                        PCLoginModel abc = null;
                        if(respond.getInt("errno") == 0){
                            abc = generateAccount(respond);
                            setLoginModel(abc);
                            setCredentials(abc.getCredentials());
                        }

                        a.responseLogin(
                                respond.getInt("errno"),
                                respond.getString("errmsg"),
                                (respond.getInt("errno") > 0)?null:abc
                        );
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            );
    }

    // update 2.0.0
    private PCLoginModel generateAccount(JSONObject respond) {
        PCLoginModel pcLogin = null;
        try {
            JSONObject resCrident = respond.getJSONObject("credentials");
            PCCredentials cc = new PCCredentials(
                    resCrident.getString("d"),
                    resCrident.getString("s"),
                    resCrident.getString("o")
            );
            setCredentials(cc);
            pcLogin = new PCLoginModel(
                    respond.getString("flying_id"),
                    respond.getString("fullname"),
                    respond.getString("photo"),
                    cc

            );
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return pcLogin;
    }

    // update 1.2.0
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
            HttpsURLConnection conn = null;
            try {
                byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
                int    postDataLength = postData.length;
                String request        = "https://progrez.cloud/s/fox/login";
                url            = new URL( request );
                conn= (HttpsURLConnection) url.openConnection();
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
                HttpsURLConnection conn= (HttpsURLConnection) url.openConnection();
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