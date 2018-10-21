package com.example.obernalp.e_games.database;

import android.content.Context;
import android.util.Log;

import com.example.obernalp.e_games.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DatabaseManager {

    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
    }


    public ArrayList<String> getListOfDatabase() {
        ArrayList<String> result = new ArrayList<>();
        String [] list;
        try {
            list = context.getAssets().list("");
            if (list.length > 0) {
                // This is a folder
                for (String file : list) {
                    if (file.contains(".txt"))
                        // This is a file
                        result.add(file.split(".txt")[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getEspiaDatabase() {
        return "espia/espia";
    }

    public ArrayList<String> getWords(String file_name){
        ArrayList<String> ret = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(file_name + ".txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                ret.add(mLine);
            }

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return ret;
    }

    public ArrayList<String> getCargosEspia(String file_name, int word){
        ArrayList<String> words = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(file_name + ".txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            int count = 0;
            while ((mLine = reader.readLine()) != null) {
                //process line
                if (!mLine.equals("")) {
                    if (mLine.startsWith("-") && count == word)
                        words.add(mLine.split("- ")[1]);
                    else if (!mLine.startsWith("-"))
                        count++;
                }
            }

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return words;
    }

    public ArrayList<String> getRolesEspia(String file_name){
        ArrayList<String> words = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(file_name + ".txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                if (!mLine.startsWith("-") && !mLine.equals(""))
                    words.add(mLine);

            }

        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return words;
    }

    // agafar una paraula
    // escriure una paraula

        // quantes paraules?

    private ArrayList<String> readWords(String path_file) {

        ArrayList<String> ret = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput(path_file + ".txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    ret.add(receiveString);
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public String readWord(int word, String path_file){
        ArrayList<String> words = readWords(path_file);
        return words.get(word);
    }

    public int getNumberOfWords(String path_file){
        ArrayList<String> words = readWords(path_file);
        return words.size();
    }

    private void writeWord(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void createDatabase(String db_name) {

    }
}
