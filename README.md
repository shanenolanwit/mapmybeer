# MapMyBeer #
## aka PubCrawler

An android app designed for tracking craft beer consumed. Users can track their own craft beer adventures and use an interactive map to explore the adventures of others, inspiring them to find and try new beers. Essentially a scavanger hunt application for craft beer lovers. :beer: :beer:

## Getting Started ##
Install Android Studio & an Android 9.0 (Pie) emulator  
* https://developer.android.com/studio/install
* https://blogs.technet.microsoft.com/karanrustagi/2017/08/15/how-to-setup-android-emulator-using-android-studio/
![Alt text](screenshots/emulator.PNG?raw=true "Emulator")

Clone the application  
```
git clone https://github.com/shanenolanwit/mapmybeer.git
```

If you have access to running instances of the [seshutils-tesco-service](https://github.com/shanenolanwit/seshutils-tesco-service "seshutils-tesco-service") and [nodemybeer](https://github.com/shanenolanwit/nodemybeer "nodemybeer") applications you can skip installing them, if not, follow the guides and set up your own instances of both services. These are the backend system of the mapmybeer android application and are required. You can run these apps locally or in the cloud, once configured, the android application just needs a url for each of them.
```
git clone https://github.com/shanenolanwit/nodemybeer.git  

git clone https://github.com/shanenolanwit/seshutils-tesco-service.git
```

### Dependencies ###
The android application has the following dependencies, using gradle to download them and sync your project, this should happen automatically if you use Android Studio.
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:1.1.3'
    implementation 'com.google.android.gms:play-services-maps:15.0.1'
    implementation 'com.google.android.gms:play-services-location:15.0.1'
    implementation 'com.google.android.gms:play-services-auth:16.0.1'
    implementation 'de.hdodenhof:circleimageview:3.0.0'
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'com.github.lecho:hellocharts-library:1.5.8@aar'
    implementation 'com.makeramen:roundedimageview:2.2.1'
    implementation "com.android.support:recyclerview-v7:28.0.0"
    implementation "com.android.support:cardview-v7:28.0.0"
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    implementation 'com.github.bumptech.glide:glide:4.9.0'

    testImplementation 'junit:junit:4.12'

    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
   
    annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'  
}
```
You will also need to create a **keys.xml** file and enter details of your google maps api key and both backend services mentioned above.
### Keys ###
Create a new file  
`app/src/main/res/values/keys.xml`  
Inside that file create the following key/value resources
* google_maps_key
    * https://developers.google.com/maps/documentation/embed/get-api-key
* tesco_lambda_url
    * https://github.com/shanenolanwit/seshutils-tesco-service
* nodemybeer_url
    * https://github.com/shanenolanwit/nodemybeer

```
<resources>
    <string name="google_maps_key">GOOGLE_MAPS_API_KEY</string>
    <string name="tesco_lambda_url">SESHUTILS_URL</string>
    <string name="nodemybeer_url">NODEMYBEER_URL</string>
</resources>
```

## Functionality ##

### Login / Register ###
![Login](screenshots/login.png?raw=true "Login")

### Navigate ###
![Navigation](screenshots/nav.png?raw=true "Nav Drawer")

### Add Beer ###
![Add Beer](screenshots/addbeer.png?raw=true "Add Beer Form")

### List / Update / Delete Beers ###
![CRUD Beer](screenshots/browsebeers.png?raw=true "Browse Beers")

### Chart Beers Discovered ###
![Chart no filters](screenshots/chart1.png?raw=true "Chart my Beer")
![Chart with filters](screenshots/chart2.png?raw=true "Chart my Beer with Filters")

### Map / Discover Beers ###
![Map no filters](screenshots/map1.png?raw=true "Map my Beer")
![Map with filters](screenshots/map2.png?raw=true "Map my Beer with Filters")

### Tesco API Integration ###
![Tesco API](screenshots/tesco.png?raw=true "Tesco API")

