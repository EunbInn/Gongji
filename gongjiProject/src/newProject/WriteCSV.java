package newProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class WriteCSV {
    public void write(ArrayList<Values> arr) {
        try {
            File file = new File("./gongji.csv");
            CSVWriter cw = new CSVWriter(new OutputStreamWriter(new FileOutputStream(file),"MS949"));
            for (int i = 0; i < arr.size(); i++) {
                Values val = arr.get(i);
                String[] valArr = {val.getDate(), val.getTitle(),val.getUrl(),val.getField(),val.getName(),val.getTel(),val.getArticle().trim()};
                cw.writeNext(valArr);
        
            }
            cw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
