package fun5i.app.api;

import android.os.AsyncTask;
import android.util.Log;

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
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

import fun5i.app.api.Model.Data;
import fun5i.app.api.Model.Maintask;
import fun5i.app.api.Model.PCCredentials;
import fun5i.app.api.Model.PCLoginModel;
import fun5i.app.api.Model.PCProjectModel;
import fun5i.app.api.Model.Project;


/**
 * version 3.0.0
 * @author fun5i
 */
public class ProgrezCloudApi {

    private static final String TAG = "ProgrezCloudApi";

    // update 3.0.0
    public static final String ALL_FIELDS = "task_name, datetime, status_done, author, description, filenya, tasktype, nominal, quantity, debitcredit, sticky, datetime_done, privacy";


    // update 2.0.0
    public interface ProgrezApiListener{
        void onSuccess();
        void onError(int errorCode, String errorMessage);
    }

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
    private int error = -1;
    private String errorMessage = "lah kok kosong";
    private boolean loginType;
    private String userkey, username, password;
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
    public String getUserkey() {
        return userkey;
    }

    public PCLoginModel getProfileUser() {
        return loginModel;
    }

    // update 2.0.0
    private void setLoginModel(PCLoginModel loginModel) {
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

    // update 2.0.0
    public boolean isLoginType() {
        return loginType;
    }

    // update 2.0.0
    public String getUsername() {
        return username;
    }

    // update 3.0.0
    private String generatePayloadProject(String tokenProject, String[] fields){
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
        }catch (ArrayIndexOutOfBoundsException|JSONException e){
            e.printStackTrace();
        }
        return result;
    }

    // update 3.0.0
    public String exampleGeneratePayload(String tokenProject, String flayingId, String fields){
        String result = null;
        try {
            JSONObject payload = new JSONObject();
            JSONObject payload2 = new JSONObject();

            payload2.put("fields", fields);
            payload2.put("limit", 100);// 100 raw

            payload.put("subtask", payload2);
            payload.put("fields", fields);
            payload.put("flying_id", flayingId);
            payload.put("token", tokenProject);

            result = payload.toString();
        }catch (ArrayIndexOutOfBoundsException|JSONException e){
            e.printStackTrace();
        }

        return result;
    }

    // update 3.0.0
    public String QueryTasks(String payload){
        String body = "";
        ConnectionMethod connectionMethod = new ConnectionMethod();
        connectionMethod.setAccount(this.credentials);
        connectionMethod.execute("task", payload);
        try{
            body = connectionMethod.get();
            JSONObject res = new JSONObject(body);
            // convert to object
            if (res.getInt("errno") == 0){
                // setCredential
                JSONObject crid = res.getJSONObject("credentials");
                setCredential(crid);
            }else{
                setError(res.getInt("errno"));
                setErrorMessage(res.getString("errmsg"));
            }
        }catch (InterruptedException|ExecutionException|JSONException e) {
            e.printStackTrace();
        }
        return body;
    }

    // update 3.0.0
    public String setProject(String tokenProject, String[] fields){
        String body = "";
        String payload = generatePayloadProject(tokenProject, fields);
        ConnectionMethod connectionMethod = new ConnectionMethod();
        connectionMethod.setAccount(this.credentials);
        connectionMethod.execute("project", payload);
        try{
            body = connectionMethod.get();
            PCProjectModel out = null;
            JSONObject res = new JSONObject(body);
            // convert to object
            if (res.getInt("errno") == 0){
                // setCredential
                JSONObject crid = res.getJSONObject("credentials");
                setCredential(crid);

                Gson gson =new Gson();
                out = gson.fromJson(
                        res.getJSONObject("data").toString(), PCProjectModel.class);
                //System.out.println("Berhasil " + projectModel.getData().getMaintask().get(0).getTaskName());
                setSemuaObjectProject(out);
            }else{
                setError(res.getInt("errno"));
                setErrorMessage(res.getString("errmsg"));
            }
        }catch (InterruptedException|ExecutionException|JSONException e) {
            e.printStackTrace();
        }

        return body;
    }

    // update 3.0.0
    public String getProject(PCCredentials credential,ProjectCallback listenerProject, String tokenProject, String[] fields) {
        String body = "";
        String payload = generatePayloadProject(tokenProject, fields);
        ConnectionMethod connectionMethod = new ConnectionMethod();
        connectionMethod.setAccount(credential);
        connectionMethod.execute("project", payload);
        try{
            body = connectionMethod.get();
            JSONObject res = new JSONObject(body);
            PCProjectModel out = null;

            // convert to object
            if (res.getInt("errno") == 0){
                // setCredential
                JSONObject crid = res.getJSONObject("credentials");
                setCredential(crid);

                Gson gson =new Gson();
                out = gson.fromJson(
                        res.getJSONObject("data").toString(), PCProjectModel.class);
                setSemuaObjectProject(out);
            }else{
                setError(res.getInt("errno"));
                setErrorMessage(res.getString("errmsg"));
            }
            // Update interface
            listenerProject.responseProject(
                    res.getInt("errno"),
                    res.getString("errmsg"),
                    out
            );

        }catch (InterruptedException|ExecutionException|JSONException e) {
            e.printStackTrace();
        }
        return body;
    }

    //update 3.0.0
    private void setCredential(JSONObject cred){
        try {
            PCCredentials newCCredentials = new PCCredentials(
                    cred.getString("d"),
                    cred.getString("s"),
                    cred.getString("o")
            );
            setCredentials(newCCredentials);
            setLoginModel(new PCLoginModel(
                    this.loginModel.getFlying_id(),
                    this.loginModel.getFullname(),
                    this.loginModel.getPhoto(),
                    newCCredentials
            ));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //update 3.0.0
    public String login(LoginCallback listener) {
        String body = "";
        ConnectionMethod connectionMethod = new ConnectionMethod();
        if (loginType){
            connectionMethod.execute("login","type=userkey&userkey="+userkey);
        }else{
            connectionMethod.execute("login","login="+username+"&password="+password);
        }
        try {
            body = connectionMethod.get();
            JSONObject respond = new JSONObject(body);
            PCLoginModel profile = null;
            if(respond.getInt("errno") == 0){
                profile = generateAccount(respond);
                setLoginModel(profile);
                setCredentials(profile.getCredentials());
            }
            listener.responseLogin(
                    respond.getInt("errno"),
                    respond.getString("errmsg"),
                    (respond.getInt("errno") > 0)?null:profile
            );
            setError(respond.getInt("errno"));
            setErrorMessage(respond.getString("errmsg"));
        } catch (InterruptedException|ExecutionException|JSONException e) {
            e.printStackTrace();
        }
        return body;
    }

    // update 2.0.0
    public ProgrezCloudApi setUserKey(String userkey){
        this.userkey = userkey;
        this.loginType = true;
        return this;
    }

    // update 2.0.0
    public ProgrezCloudApi setUserLogin(String username, String password){
        this.username = username;
        this.password = password;
        this.loginType = false;
        return this;
    }

    // update 3.0.0
    public String loadNewCridential(){
        String body = "";
        ConnectionMethod connectionMethod = new ConnectionMethod();
        if (loginType){
            connectionMethod.execute("login","type=userkey&userkey="+userkey);
        }else{
            connectionMethod.execute("login","login="+username+"&password="+password);
        }
        try {
            body = connectionMethod.get();
            JSONObject respond = new JSONObject(body);
            if (respond.getInt("errno") > 0){
                this.error = respond.getInt("errno");
                this.errorMessage = respond.getString("errmsg");
            }else{
                //success set loginModel and credential
                setLoginModel(generateAccount(respond));
            }
        } catch (InterruptedException|ExecutionException|JSONException e) {
            e.printStackTrace();
        }
        return body;
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
            Log.i(TAG, "generateAccount: "+ex.getMessage());
        }
        return pcLogin;
    }

    // update 3.0.0
    class ConnectionMethod extends AsyncTask<String, String, String> {
        private String body;


        PCCredentials accon;
        void setAccount(PCCredentials ac){
            this.accon = ac;
        }

        @Override
        protected String doInBackground(String... parms) {
            if (parms[0].equals("login")){
                body = actLogin(parms[1]);
            }else if(parms[0].equals("project")){
                body = actProject(accon, parms[1]);
            }else if(parms[0].equals("task")){
                body = actTasks(accon, parms[1]);
            }
            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
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

        private String actProject(PCCredentials credentials, String payload){
            String result = null;
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

        private String actTasks(PCCredentials credentials, String payload){
            String result = null;
            try {
                byte[] postData       = payload.getBytes( StandardCharsets.UTF_8 );
                int    postDataLength = postData.length;
                String request        = "https://progrez.cloud/s/fox/task";
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