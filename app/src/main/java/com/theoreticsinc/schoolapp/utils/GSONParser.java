package com.theoreticsinc.schoolapp.utils;


/**
 * Created by Angelo on 9/23/2015.
 */

import android.content.Context;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.theoreticsinc.schoolapp.models.ConfigItems;
import com.theoreticsinc.schoolapp.models.ConfigModel;
import com.theoreticsinc.schoolapp.models.ConfigPage;
import com.theoreticsinc.schoolapp.models.NewsletterItems;
import com.theoreticsinc.schoolapp.models.NewsletterModel;
import com.theoreticsinc.schoolapp.models.NewsletterPage;

public class GSONParser {

    final String TAG = "GsonParser.java";
    static final String SERVER = "http://184.95.54.213/standrewsmobile/configuration.json";

    public List<String> id;
    public List<String> name;
    public List<String> pic_url;
    public List<String> details;
    MemoryCache memoryCache=new MemoryCache();

    public GSONParser() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        id = new ArrayList<>();
        name = new ArrayList<>();
        pic_url = new ArrayList<>();
        details = new ArrayList<>();
    }

    public static String readGSONfromServer(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static void main(String[] args) throws Exception {
        //new JSONParser().readGSONfromServer(urlString);
        String url = readGSONfromServer(SERVER);
        //http://184.95.54.213/schoolapp/newsletters.json
        //http://www.javascriptkit.com/dhtmltutors/javascriptkit.json
        Gson gson = new Gson();
        ConfigPage page = gson.fromJson(url, ConfigPage.class);
        ConfigModel config = page.config;
        //System.out.println(page.newsletters);
        for (ConfigItems item : config.items)
            System.out.println("    " + item.id + "    " + item.type + " " + item.url);
    }

    public String readConfig(String type) {
        try {
            String configURL = readGSONfromServer(SERVER);
            Gson gson = new Gson();
            ConfigPage page = gson.fromJson(configURL, ConfigPage.class);
            ConfigModel config = page.config;
            //System.out.println(page.newsletters);
            for (ConfigItems item : config.items) {
                System.out.println("    " + item.id + "    " + item.type + " " + item.url);
                String configType = item.type.toString();
                if (0 == configType.compareToIgnoreCase(type)) {
                    return item.url;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void processDataFromGSON(String urlString, Context context) throws Exception{
        String dataFromServer = "";
        String dataFromSDCard = "";
        try {
            dataFromServer = readGSONfromServer(urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //READ DIRECT FROM SERVER
        if (null != dataFromServer && dataFromServer.isEmpty() == false) {
            Gson gson = new Gson();
            NewsletterPage page = gson.fromJson(dataFromServer, NewsletterPage.class);
            NewsletterModel newsletters = page.newsletters;
            System.out.println(page.newsletters);
            for (NewsletterItems item : newsletters.items) {
                System.out.println("CLOUD ITEMS:    " + item.name + "    " + item.details + " " + item.pic_url);
                id.add(item.id);
                name.add(item.name);
                pic_url.add(item.pic_url);
                details.add(item.details);
            }
            //SAVE TO SDCARD
            FileCache fileCache = new FileCache(context);
            fileCache.downloadFile2SD(urlString, "schoolalertsList.gson");
        }

        //CHECK FROM SDCARD
        else {
            FileCache fileCache = new FileCache(context);
            if (fileCache.isExternalStorageReadable()) {
                //FileCache returns String from SDCARD
                dataFromSDCard = fileCache.getGSONFromSDCache();
                Gson gson = new Gson();
                NewsletterPage page = gson.fromJson(dataFromSDCard, NewsletterPage.class);
                NewsletterModel newsletters = page.newsletters;
                //System.out.println(page.newsletters);
                for (NewsletterItems item : newsletters.items) {
                    System.out.println("SD ITEMS:    " + item.name + "    " + item.details + " " + item.pic_url);
                    id.add(item.id);
                    name.add(item.name);
                    pic_url.add(item.pic_url);
                    details.add(item.details);

                }
            }
        }
    }
}