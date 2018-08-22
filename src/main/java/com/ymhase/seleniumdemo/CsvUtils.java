package com.ymhase.seleniumdemo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvUtils {

	public static List<CSVRecord> readCSV(String path) {
		Reader in = null;
		CSVParser parser = null;
		try {
			in = new FileReader(path);
			parser = CSVFormat.RFC4180.withHeader().parse(in);
			List<CSVRecord> list = parser.getRecords();
			return list;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ioe) {
					throw new RuntimeException(ioe);
				}
			}

			if (parser != null) {
				try {
					parser.close();
				} catch (IOException ioe) {
					throw new RuntimeException(ioe);
				}
			}
		}
	}

}
