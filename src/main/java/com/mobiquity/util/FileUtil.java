package com.mobiquity.util;

import com.mobiquity.exception.FileParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final Logger logger = LogManager.getLogger(FileUtil.class);

    /**
     * Reads a file from given path and return list of items
     * @param filePath absolute path
     * @return List of String having items
     * @throws FileNotFoundException
     */
    public static List<String> readFile(String filePath) throws FileNotFoundException {
        if (null == filePath || filePath.isBlank()) {
            logger.error("File path is either empty or null");
            throw new FileNotFoundException("File path is empty or null");
        }

        List<String> results = new ArrayList<>();
        try {
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                results.add(line);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            throw new FileNotFoundException(fe.getMessage());
        }
        catch (FileParserException | UnsupportedEncodingException fpe) {
            logger.error(fpe.getMessage() );
            throw new FileParserException(fpe.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage() );
            throw new RuntimeException(e);
        }
        return results;
    }

}
