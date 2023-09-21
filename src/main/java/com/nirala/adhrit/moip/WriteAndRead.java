package com.meta.verse.moip;

        import java.io.*;
        import java.nio.file.Files;
        import java.nio.file.Paths;

public class WriteAndRead {

    /**
     * This class shows how to write file in java
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException {
        String data = "I will write this String to File in Java";
        int noOfLines = 10000;
//        writeUsingFileWriter(data);

            writeUsingBufferedWriter();

//        writeUsingFiles(data);
//
//        writeUsingOutputStream(data);
        System.out.println("DONE");
    }

    /**
     * Use Streams when you are dealing with raw data
     * @param data
     */
    private static void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("/Users/pankaj/os.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Use Files class from Java 1.7 to write files, internally uses OutputStream
     * @param data
     */
    private static void writeUsingFiles(String data) {
        try {
            Files.write(Paths.get("/Users/pankaj/files.txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Use BufferedWriter when number of write operations are more
     * It uses internal buffer to reduce real IO operations and saves time
     * //@param data
     * //@param noOfLines
     */
    private static void writeUsingBufferedWriter() throws FileNotFoundException {
        File file = new File("C:\\Users\\Raman.Nirala\\Downloads\\WorkSpace\\BufferedWriter.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        //String dataWithNewLine=data+System.getProperty("line.separator");

        String line = "";
        String splitBy = ",";
        BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Raman.Nirala\\Downloads\\WorkSpace\\moip\\MOIP_5th_Oct_Data.csv"));

        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            while ((line= br1.readLine())!=null) {
                br.write(line+System.getProperty("line.separator"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Use FileWriter when number of write operations are less
     * @param data
     */
    private static void writeUsingFileWriter(String data) {
        File file = new File("C:\\Users\\Raman.Nirala\\Downloads\\WorkSpace\\FileWriter.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


//		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
//		Date finalResult = df1.parse(minDate);

//		ISO8601DateFormat df = new ISO8601DateFormat();
//		Date date = df.parse(minDate);

//Calendar calendar1 = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()) ;
//		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
//		Date date = dateformat.parse(minDate);