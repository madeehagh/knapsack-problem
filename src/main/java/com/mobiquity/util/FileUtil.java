package com.mobiquity.util;

import com.mobiquity.constants.MessageConstants;
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
            String errorMessage = MessageConstants.INVALID_FILE_PATH;
            logger.error(errorMessage);
            throw new FileNotFoundException(errorMessage);
        }

        List<String> results = new ArrayList<>();
        try {
        File file = new File(filePath);

        if (! file.exists() || file.isDirectory()){
            throw new FileNotFoundException(MessageConstants.FILE_NOT_FOUND);
        }

        BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                results.add(line);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            throw fe;
        } catch (IOException e) {
            logger.error(e.getMessage() );
            throw new RuntimeException(e);
        }
        return results;
    }

}
