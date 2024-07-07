package com.news.spring_newsapi.websites;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HiphopDX {






    public static LinkedHashMap<String,String[]> getNews() throws IOException {

        LinkedHashMap<String,String[]> news  = new LinkedHashMap<>();

        Elements mainContent = getdocument("https://hiphopdx.com/news/").getElementsByClass("secondary-posts");

//        Elements topics = mainContent.getFirst().getElementsByTag("a");
        Elements topics = mainContent.get(0).getElementsByTag("a");





        for (Element topic : topics) {
            String url = "https://hiphopdx.com" + topic.attr("href");
            Elements storyContentContainer = getdocument(url).getElementsByClass("content-container");
//            display(storyContentContainer);
            String[] result = getStory(storyContentContainer);
            news.put(result[0], new String[]{url ,result[1], result[2],result[3]});


        }
        return news;
    }

    static Document getdocument(String url) throws IOException {

        return Jsoup.connect(url).get();

    }


    static  String[] getStory(Elements storyContentContainer){


        String image = "";
        String title ="";
        String story = "";
        String date = "";





        for (Element storyContent : storyContentContainer) {

            date = storyContent.getElementsByClass("date").attr("content");
            image = storyContent.getElementsByClass("image large").first().getElementsByTag("img").attr("src");
            title = storyContent.getElementsByClass("headline__title").last().text();
            story = storyContent.getElementsByClass("body-copy").last().text().replace("AD AD LOADING...","he");



//            System.out.println( image+ "hmm");
//            System.out.println( title);
//            System.out.println(story+"\n");





        }
        return new String[]{title,date,image,story};
//        return title +"<br>" + story+"<br><br>";


    }


}
