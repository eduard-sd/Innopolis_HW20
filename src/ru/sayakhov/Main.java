package ru.sayakhov;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL(" http://musicbrainz.org/ws/2/artist/5b11f4ce-a62d-471e-81fc-a69a8278c7da?inc=aliases&fmt=json");
        try (InputStream stream = url.openStream();
             Reader reader = new InputStreamReader(stream);
             BufferedReader br = new BufferedReader(reader);
        ) {
            StringBuilder sb = new StringBuilder();

            while (br.ready()) {
                sb.append(br.readLine());
            }
            //System.out.println(sb);
            Music music = new Music();
            String json = sb.toString();
            Reader reader1 = new StringReader(json);
            ObjectMapper mapper = new ObjectMapper();
            //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sb.toString()));
            music = mapper.readValue(reader1, Music.class);
            System.out.println(music);


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
