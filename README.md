# ProgrezCloudApiAndroid

Untuk mengurangi waktu ngopi, saya meringkas penulisan kode untuk mengambil data dari project teman2.
Kodingan ini melputi <b>Authentication User</b>, dan <b>Reading Tasks</b>. Lihatlah bagai mana saya mendapatkannya.

```java
progrezCloudApi.login((err, msg, objAccount)->{}, "userkey");
```
```java
progrezCloudApi.login((err, msg, objAccount)->{}, "username", "password");
```
```java
progrezCloudApi.getProject(objAccount, (err, msg, body)->{}, "token_project", "fields");
```

Untuk fields silahkan pilih yang ingin di tampilkan <b><i>task_name , datetime , status_done , author, description , 
filenya , tasktype , nominal , quantity , debitcredit , sticky , datetime_done , privacy </i></b> dan untuk menampilkan semua 
fields gunakan <b><i>all</i></b>.

## contoh penggunaan
```java
ProgrezCloudApi progrezCloudApi = new ProgrezCloudApi(); // initial class
progrezCloudApi.login((int errno, String errmsg, PCLoginModel account) -> {
  if (errno == 0)
    progrezCloudApi.getProject(account, (int errno2, String errmsg2, String body) -> {
      setNama(account.getFullName());             // menampilkan namamu
      setGambarProfile(account.getProfile());     // menampilkan gambar profile
      tampilkanProject(body);                     // menampilkan project
    },"TOKEN_PROJECT", "task_name, nominal");
    
},"USER_KEY");
```
## implementation
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
```
dependencies {
  implementation 'com.github.AgungDev:ProgrezCloudApiAndroid:-SNAPSHOT'
}
```
