package com.news.spring_newsapi.websites;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HiphopDX {






    public static String getNews() throws IOException {

        String news = "";

        Elements mainContent = getdocument("https://hiphopdx.com/news/").getElementsByClass("secondary-posts");

//        Elements topics = mainContent.getFirst().getElementsByTag("a");
        Elements topics = mainContent.get(0).getElementsByTag("a");





        for (Element topic : topics) {

            Elements storyContentContainer = getdocument("https://hiphopdx.com" + topic.attr("href")).getElementsByClass("content-container");
//            display(storyContentContainer);
            news += getStory(storyContentContainer);

        }
        return news;
    }

    static Document getdocument(String url) throws IOException {

        return Jsoup.connect(url).get();

    }


    static  String getStory(Elements storyContentContainer){
        String title = "";
        String story = "";



        for (Element storyContent : storyContentContainer) {


            title = storyContent.getElementsByClass("headline__title").last().text();
            story = storyContent.getElementsByClass("body-copy").last().text();



            System.out.println( title);
            System.out.println(story+"\n");




        }
        return title +"<br>" + story+"<br><br>";


    }


}
