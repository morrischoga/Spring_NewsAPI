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

            Elements storyContentContainer = getdocument("https://hiphopdx.com" + topic.attr("href")).getElementsByClass("content-container");
//            display(storyContentContainer);
            String[] result = getStory(storyContentContainer);
            news.put(result[0], new String[]{result[1], result[2]});


        }
        return news;
    }

    static Document getdocument(String url) throws IOException {

        return Jsoup.connect(url).get();

    }


    static  String[] getStory(Elements storyContentContainer){
        String image = "";
        String title = "";
        String story = "";




        for (Element storyContent : storyContentContainer) {


            image = storyContent.getElementsByClass("image large").first().getElementsByTag("img").attr("src");
            title = storyContent.getElementsByClass("headline__title").last().text();
            story = storyContent.getElementsByClass("body-copy").last().text().replace("AD AD LOADING...","");



//            System.out.println( image+ "hmm");
//            System.out.println( title);
//            System.out.println(story+"\n");





        }
        return new String[]{title,image,story};
//        return title +"<br>" + story+"<br><br>";


    }


}
