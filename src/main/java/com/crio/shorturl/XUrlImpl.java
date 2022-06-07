package com.crio.shorturl;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class XUrlImpl implements XUrl {

    private Map<String,String> urlMap = new HashMap<>();
    private Map<String,Integer> count = new HashMap<>();
    private Map<String,String> reverseMap = new HashMap<>();
    

    
    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        // TODO Auto-generated method stub
        if(reverseMap.containsKey(shortUrl)){
            return null;
        }
        else {
            urlMap.put(longUrl , shortUrl);
            reverseMap.put(shortUrl , longUrl);
            return shortUrl;
        }
    }

    @Override
    public String getUrl(String shortUrl) {
        // TODO Auto-generated method stub
        String ans = "";
        
        if(reverseMap.containsKey(shortUrl)){
            ans = reverseMap.get(shortUrl);

            if(count.containsKey(ans)){
                int c = count.get((ans));
                c += 1;
                count.put(ans , c);
            }
            else{
                count.put(ans , 1);
            }
            return ans;
        }
        else{
            return null;
        }
    }

    @Override
    public Integer getHitCount(String longUrl) {
        // TODO Auto-generated method stub
        if(count.containsKey(longUrl)){
            return count.get(longUrl);
        }
        else {
            return 0;
        }
    }

    @Override
    public String delete(String longUrl) {
        // TODO Auto-generated method stub
        reverseMap.remove(urlMap.get(longUrl));
        urlMap.remove(longUrl);

        return null;
    }

    @Override
    public String registerNewUrl(String longUrl) {
        // TODO Auto-generated method stub
        Random r = new Random();
        
        //creating map for storing longurl and its short url

        //if longurl already exist
        if(urlMap.containsKey(longUrl)){
            return urlMap.get(longUrl);            
        }
        else{
            //if longurl isn't existed then register and return
            String shortUrlPrefix = "http://short.url/";
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < 9; i++){
                int alphanumeric = r.nextInt(3);
                if(alphanumeric == 0){
                    char ch = (char)(r.nextInt(10)+48);
                    sb.append(ch);
                }
                else if(alphanumeric == 1){
                    char ch = (char)(r.nextInt(26)+65);
                    sb.append(ch);
                }
                else{
                    char ch = (char)(r.nextInt(26)+97);
                    sb.append(ch);
                }
            }
            String shortUrlSuffix = sb.toString();
            String shortUrl = shortUrlPrefix + shortUrlSuffix;
            urlMap.put(longUrl , shortUrl);
            reverseMap.put(shortUrl , longUrl);
            return urlMap.get(longUrl);
        }
        
    }
}